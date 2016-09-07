package fr.afcepf.atod.wine.data.product.api;


import fr.afcepf.atod.vin.entity.Product;
import fr.afcepf.atod.wine.data.api.IDaoGeneric;

public interface IDaoProduct extends IDaoGeneric<Product, Integer> {
	
	Product findByName(String name);
	
}
