package fr.afcepf.atod.wine.data.product.api;


import java.util.List;

import fr.afcepf.atod.wine.data.api.IDaoGeneric;
import fr.afcepf.atod.wine.entity.Product;

public interface IDaoProduct extends IDaoGeneric<Product, Integer> {
	
	Product findByName(String name);
	List<Product> getPromotedProductsSortedByEndDate(Integer max);
}
