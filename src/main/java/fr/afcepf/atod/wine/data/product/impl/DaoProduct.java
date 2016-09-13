package fr.afcepf.atod.wine.data.product.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import fr.afcepf.atod.wine.data.impl.DaoGeneric;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.entity.Product;


@Service
@Transactional
public class DaoProduct extends DaoGeneric<Product, Integer> implements IDaoProduct{
	 /*****************************************************.
     *                  Requetes HQL
     ****************************************************/

    	private static final String REQFINDBYNAME = "SELECT p FROM Product p "
                + "WHERE p.name = :name";
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
	public Product findByName(String name) {
		Product p = null;
		p = (Product)(getSf().getCurrentSession()
			.createQuery(REQFINDBYNAME)
			.setParameter("name", name)
			.uniqueResult());
		
		return p;
	}

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
