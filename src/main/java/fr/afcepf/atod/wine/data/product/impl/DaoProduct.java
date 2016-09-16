package fr.afcepf.atod.wine.data.product.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.afcepf.atod.vin.data.exception.WineErrorCode;
import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.impl.DaoGeneric;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;

@Service
@Transactional
public class DaoProduct extends DaoGeneric<Product, Integer> implements IDaoProduct {

    /**
     * ****************************************************
     * Requetes HQL ***********************************************
     */
    private static final String REQFINDBYNAME = "SELECT p FROM Product p " + "WHERE p.name like :name";
    private static final String REQEXPPROD = "SELECT p FROM " + "Product p WHERE p.price > :paramMin";
    private static final String REQGETPROMOTEDPRODUCTSSORTEDBYENDDATE = "SELECT"
            + " p FROM Product p LEFT JOIN FETCH p.productSuppliers WHERE 0 < ANY"
            + "(SELECT ps.quantity from ProductSupplier ps WHERE ps.pk.product=p)" + " AND  p.speEvent IS NOT NULL";
    private static final String REQAPPELLATIONSBYWINETYPE = "SELECT DISTINCT p.appellation "
            + "FROM Product p WHERE p.productType = :type";
    private static final String REQVARIETALSBYWINETYPE = "SELECT  DISTINCT(pv) "
            + "FROM ProductVarietal pv LEFT JOIN pv.productsWine pw " + "LEFT JOIN pw.productType pt WHERE pt = :type";

    private static final String REQFINDBYAPPELATION = "SELECT p FROM Product p WHERE p.appellation like :paramApp";
    private static final String REQFINDBYTYPE = "SELECT distinct(pt) FROM ProductType pt left join fetch pt.productsWine where pt.type like :paramType";
    private static final String REQFINDBYVINTAGE = "SELECT distinct(pv) FROM ProductVintage pv left join fetch pv.productsWine"
            + "  where pv.year =:paramVintage";

    private static final String REQFINDBYVARIETAL = "SELECT distinct(pv) FROM ProductVarietal pv left join fetch pv.productsWine pw"
            + "  where pw.description like :paramVarietal";

    private static final String REQTYPEVARITAL = "SELECT distinct(p) FROM ProductWine p"
            + " right join fetch productType  "
            + "right join fetch productVarietal  WHERE "
            + "p.productType like : paramPT AND p.productVarietal like :paramPV";
//	from Cat as cat
//    inner join fetch cat.mate
//    left join fetch cat.kittens child
//    left join fetch child.kittens

    /**
     * ********************************************
     * Requetes HQL ********************************************
     */
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
            p = (Product) (getSf().getCurrentSession().createQuery(REQFINDBYNAME).setParameter("name", "%" + name + "%")
                    .uniqueResult());
            if (p == null) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "the product named " + name + " has not been" + " found in the database.");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "the product named " + name + " has not been" + " found in the database.");
        }
        return p;
    }

    /**
     *
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getPromotedProductsSortedByEndDate(Integer max) {
        List<Product> l = null;
        l = getSf().getCurrentSession().createQuery(REQGETPROMOTEDPRODUCTSSORTEDBYENDDATE).setMaxResults(max).list();
        return l;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findExpensiveProducts(double min) throws WineException {
        List<Product> expensiveProds = null;
        if (min >= 0) {
            expensiveProds = getSf().getCurrentSession().createQuery(REQEXPPROD).setParameter("paramMin", min).list();
            if (expensiveProds.isEmpty()) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "expensive products not found in the database");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE, "criteria has to be defined...");
        }

        return expensiveProds;
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
    /**
     * 
     * @param appelation
     * @return
     * @throws WineException 
     */
    @SuppressWarnings({"unchecked"})
    @Override
    public List<Product> findByAppelation(String appelation) throws WineException {
        List<Product> list = null;
        if (!appelation.equals("")) {
            list = getSf().getCurrentSession().createQuery(REQFINDBYAPPELATION)
                    .setParameter("paramApp", "%" + appelation + "%").list();
            if (list == null) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "the product list named " + list + " has not been"
                        + " found in the database.");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "the product list named " + list + " has not been"
                    + " found in the database.");
        }
        return list;
    }
    /**
     * 
     * @param vintage
     * @return
     * @throws WineException 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductVintage> findByVintage(Integer vintage) throws WineException {
        List<ProductVintage> list = null;
        list = getSf().getCurrentSession().createQuery(REQFINDBYVINTAGE).setParameter("paramVintage", vintage).list();
        return list;
    }
    /**
     * 
     * @param variatal
     * @return
     * @throws WineException 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductVarietal> findByVariatal(String variatal) throws WineException {
        List<ProductVarietal> list = null;
        if (!variatal.equals("")) {
            list = getSf().getCurrentSession().createQuery(REQFINDBYVARIETAL)
                    .setParameter("paramVarietal", "%" + variatal + "%").list();
            if (list == null) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "the productVarietal list named " + list + " has not been"
                        + " found in the database.");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "the productVarietal list named " + list + " has not been"
                    + " found in the database.");
        }

        return list;

    }
    /**
     * 
     * @param wineType
     * @return
     * @throws WineException 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductType> findByType(String wineType) throws WineException {
        List<ProductType> list = null;
        if (!wineType.equals("")) {
            list = getSf().getCurrentSession().createQuery(REQFINDBYTYPE)
                    .setParameter("paramType", "%" + wineType + "%").list();
            if (list == null) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "the productVarietal list named " + list + " has not been"
                        + " found in the database.");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "the productVarietal list named " + list + " has not been"
                    + " found in the database.");
        }

        return list;
    }
    /**
     * 
     * @param name
     * @return
     * @throws WineException 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findByNotCompleteName(String name) throws WineException {
        List<Product> list = null;
        if (!name.equals("")) {
            list = getSf().getCurrentSession().createQuery(REQFINDBYNAME).setParameter("name", "%" + name + "%").list();
            if (list == null) {
                throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                        "the product list named " + list + " has not been" + " found in the database.");
            }
        } else {
            throw new WineException(WineErrorCode.RECHERCHE_NON_PRESENTE_EN_BASE,
                    "the product list named " + list + " has not been" + " found in the database.");
        }
        return list;
    }
    /**
     * 
     * @param wineType
     * @param varietal
     * @return
     * @throws WineException 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findByVarietalAndType(String wineType, String varietal) throws WineException {
        List<Product> list = null;
        list = getSf().getCurrentSession().createQuery(REQTYPEVARITAL).
                setParameter("paramPT", "%" + wineType + "%").setParameter("paramPV", varietal).list();
        return list;
    }

    /**
     * 
     * @param type
     * @return
     * @throws WineException 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAppellationsByWineType(ProductType type) throws WineException {
        List<String> l = null;
        l = getSf().getCurrentSession().createQuery(REQAPPELLATIONSBYWINETYPE).setParameter("type", type).list();
        return l;
    }
}
