package fr.afcepf.atod.wine.data.product.api;

import java.util.List;

import fr.afcepf.atod.vin.data.exception.WineException;
import fr.afcepf.atod.wine.data.api.IDaoGeneric;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;

import fr.afcepf.atod.wine.entity.ProductVintage;
import fr.afcepf.atod.wine.entity.ProductWine;

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
    /**
     *
     * @param type
     * @return
     * @throws WineException
     */
    List<String> getAppellationsByWineType(ProductType type) throws WineException;
    /**
     *
     * @param type
     * @return
     * @throws WineException
     */
    List<ProductVarietal> getVarietalsByWineType(ProductType type) throws WineException;
    /**
     *
     * @param name
     * @return
     * @throws WineException
     */
    List<Product> findByNotCompleteName(String name) throws WineException;
    /**
     *
     * @param appelation
     * @return
     * @throws WineException
     */
    List<Product> findByAppelation(String appelation) throws WineException;
    /**
     *
     * @param vintage
     * @return
     * @throws WineException
     */
    List<ProductVintage> findByVintage(Integer vintage) throws WineException;
    /**
     *
     * @param variatal
     * @return
     * @throws WineException
     */
    List<ProductVarietal> findByVarietal(String variatal) throws WineException;
    /**
     *
     * @param wineType
     * @return
     * @throws WineException
     */
    List<ProductType> findByType(String wineType) throws WineException;
    /**
     *
     * @param wineType
     * @param Varietal
     * @return
     * @throws WineException
     */
    List<ProductWine> findByVarietalAndType(ProductType wineType, ProductVarietal Varietal) 
            throws WineException;
    /**
     * 
     * @param type
     * @param vintage
     * @return
     * @throws WineException 
     */
    List<ProductWine> findByVintageAndType(ProductType type, ProductVintage vintage)
            throws WineException;
    /**
     * 
     * @param type
     * @param integ
     * @return
     * @throws WineException 
     */
    List<ProductWine> findByMoneyAndType(ProductType type, int integ) throws WineException;
    /**
     * 
     * @param type
     * @param integ
     * @param maxInt
     * @return
     * @throws WineException 
     */
    List<ProductWine> findByMoneyAndType(ProductType type, Integer integ, Integer maxInt) throws WineException;
}
