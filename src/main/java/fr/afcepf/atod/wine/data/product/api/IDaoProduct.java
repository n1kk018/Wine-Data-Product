package fr.afcepf.atod.wine.data.product.api;

import java.util.List;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.api.IDaoGeneric;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;

import fr.afcepf.atod.wine.entity.ProductVintage;

public interface IDaoProduct extends IDaoGeneric<Product, Integer> {
	/**
	 * 
	 * @param max
	 * @return
	 */
	List<Product> getPromotedProductsSortedByEndDate(Integer max);

	/**
	 * 
	 * @param name
	 * @return
	 * @throws WineException
	 */
	Product findByName(String name) throws WineException;

        /**
         * 
         * @param min
         * @return
         * @throws WineException 
         */
	  List<Product> findExpensiveProducts(double min) throws WineException;
	 
	  List<String> getAppellationsByWineType(ProductType type) throws WineException;
	  
	  List<ProductVarietal> getVarietalsByWineType(ProductType type) throws WineException;


	List<Product> findByNotCompleteName(String name) throws WineException;

	List<Product> findByAppelation(String appelation) throws WineException;

	List<ProductVintage> findByVintage(Integer vintage) throws WineException;

	List<ProductVarietal> findByVariatal(String variatal) throws WineException;
	
	List<ProductType> findByType(String wineType) throws WineException;

}
