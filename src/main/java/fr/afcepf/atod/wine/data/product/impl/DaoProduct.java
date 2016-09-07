package fr.afcepf.atod.wine.data.product.impl;

import javax.management.Query;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afcepf.atod.vin.entity.Product;
import fr.afcepf.atod.wine.data.impl.DaoGeneric;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;


@Service
@Transactional
public class DaoProduct extends DaoGeneric<Product, Integer> implements IDaoProduct{
	 /*****************************************************.
     *                  Requetes HQL
     ****************************************************/

    	private static final String REQFINDBYNAME = "SELECT p FROM Product p WHERE p.name= :name";

    /****************************************************.
     *                 Fin Requetes HQL
     ****************************************************/
	
	
	@Override
	public Product findByName(String name) {
		Product p;
		p = (Product)getSf().getCurrentSession()
			.createQuery(REQFINDBYNAME)
			.setParameter("name", name)
			.uniqueResult();
		
		return p;
	}
}
