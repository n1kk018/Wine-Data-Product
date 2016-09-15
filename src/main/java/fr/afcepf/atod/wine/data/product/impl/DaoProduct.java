package fr.afcepf.atod.wine.data.product.impl;

import fr.afcepf.atod.vin.data.exception.WineErrorCode;
import fr.afcepf.atod.vin.data.exception.WineException;
import javax.management.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import fr.afcepf.atod.wine.data.impl.DaoGeneric;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;

import java.util.List;

@Service
@Transactional
public class DaoProduct extends DaoGeneric<Product, Integer> implements IDaoProduct {

    /**
     * ****************************************************
     * Requetes HQL 
     ***************************************************/
    private static final String REQFINDBYNAME = "SELECT p FROM Product p "
            + "WHERE p.name = :name";
    private static final String REQEXPPROD = "SELECT p FROM "
            + "Product p WHERE p.price > :paramMin";
    private static final String REQGETPROMOTEDPRODUCTSSORTEDBYENDDATE = "SELECT"
            + " p FROM Product p LEFT JOIN FETCH p.productSuppliers WHERE 0 < ANY"
            + "(SELECT ps.quantity from ProductSupplier ps WHERE ps.pk.product=p)"
            + " AND  p.speEvent IS NOT NULL";
    private static final String REQAPPELLATIONSBYWINETYPE = "SELECT DISTINCT p.appellation "
    		+ "FROM Product p WHERE p.productType = :type";
    private static final String REQVARIETALSBYWINETYPE = "SELECT  p "
    		+ "FROM Product p LEFT JOIN FETCH p.productVarietal "
    		+ "RIGHT JOIN p.productType WHERE p.productType = :type";
            

    /** ********************************************** 
     * Fin Requetes HQL
     *************************************************/
    
    /**
     *
     * @param name
     * @return
     *
     */
    @Override
    public Product findByName(String name) throws WineException {
        Product p = null;
        if (!name.equals("")) {
            p = (Product) (getSf().getCurrentSession()
                    .createQuery(REQFINDBYNAME)
                    .setParameter("name", name)
                    .uniqueResult());
            if (p == null) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "the product named " + name + " has not been"
                        + " found in the database.");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "the product named " + name + " has not been"
                    + " found in the database.");
        }
        return p;
    }
     @SuppressWarnings("unchecked")
	@Override
    public List<Product> findExpensiveProducts(double min) throws WineException {
        List<Product> expensiveProds = null;
        if (min >= 0) {
            expensiveProds = getSf().getCurrentSession().createQuery(REQEXPPROD)
                    .setParameter("paramMin", min).list();
            if (expensiveProds.isEmpty()){
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "expensive products not found in the database");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "criteria has to be defined...");
        }
        
        return expensiveProds;
    }


    /**
     *
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getPromotedProductsSortedByEndDate(Integer max) {
        List<Product> l = null;
        l = getSf().getCurrentSession()
                .createQuery(REQGETPROMOTEDPRODUCTSSORTEDBYENDDATE)
                .setMaxResults(max)
                .list();
        return l;
    }
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAppellationsByWineType(ProductType type) throws WineException {
		List<String> l = null;
        l = getSf().getCurrentSession()
                .createQuery(REQAPPELLATIONSBYWINETYPE)
                .setParameter("type", type)
                .list();
        return l;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductVarietal> getVarietalsByWineType(ProductType type) throws WineException {
		List<ProductVarietal> l = null;
        l = getSf().getCurrentSession()
                .createQuery(REQVARIETALSBYWINETYPE)
                .setParameter("type", type)
                .list();
        return l;
	}
}
