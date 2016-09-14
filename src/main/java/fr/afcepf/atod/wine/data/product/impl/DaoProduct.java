package fr.afcepf.atod.wine.data.product.impl;

import fr.afcepf.atod.vin.data.exception.WineErrorCode;
import fr.afcepf.atod.vin.data.exception.WineException;
import javax.management.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import fr.afcepf.atod.wine.data.impl.DaoGeneric;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.entity.Product;
import java.util.List;

@Service
@Transactional
public class DaoProduct extends DaoGeneric<Product, Integer> implements IDaoProduct {

    /******************************************************
     *                  Requetes HQL
     * **************************************************  */
    private static final String REQFINDBYNAME = "SELECT p FROM Product p "
            + "WHERE p.name = :name";
    private static final String REQEXPPROD = "SELECT p FROM "
            + "Product p WHERE p.price > :paramMin";
    private static final String REQGETPROMOTEDPRODUCTSSORTEDBYENDDATE = "SELECT"
    			+ " p FROM Product p WHERE p.speEvent IS NOT NULL "
    			+ "ORDER BY p.speEvent.endDate ASC";

    /****************************************************.
     *                 Fin Requetes HQL
     ****************************************************/
    /**
     *
     * @param name
     * @return
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
	/**
         * 
         * @param name
         * @return 
         */
	@Override
	public Product findByName(String name) {
		Product p = null;
		p = (Product)(getSf().getCurrentSession()
			.createQuery(REQFINDBYNAME)
			.setParameter("name", name)
			.uniqueResult());
		
		return p;
	}
        /**
         * 
         */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getPromotedProductsSortedByEndDate(Integer max) {
		List<Product> l = null;
		l=getSf().getCurrentSession()
				.createQuery(REQGETPROMOTEDPRODUCTSSORTEDBYENDDATE)
				.setMaxResults(max)
				.list();
		return l;
	}
}
