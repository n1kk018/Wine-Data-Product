package fr.afcepf.atod.wine.data.product.api;


import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.api.IDaoGeneric;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;

import java.util.List;

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
}
