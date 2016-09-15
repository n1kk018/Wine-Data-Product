package fr.afcepf.atod.wine.data.product.test;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.data.product.api.IDaoProductType;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;
import fr.afcepf.atod.wine.entity.ProductVintage;

public class daoTester {
	private static Logger log = Logger.getLogger(daoTester.class);

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
			IDaoProduct dao = (IDaoProduct) bf.getBean(IDaoProduct.class);
			/*
			 * Product p1 = dao.findObj(9); log.info(p1); Product p2 =
			 * dao.findByName("Maquis Cabernet Sauvignon 2012"); log.info(p2);
			 */
			// List<Product> list= dao.findAllObj();
			// List<Product> list = dao.getPromotedProductsSortedByEndDate(10);
			// for (Product product : list) {
			// log.info(product);
			// }
			Product proTest = dao.findByName("Chateau du Cedre Cahors Le Cedre 2011");
			log.info("#################" + proTest.getIdProduct() + " " + proTest.getName() + "##############");

			// List<Product> list=dao.findByNotCompleteName("Jean");
			// List<Product> list=dao.findByAppelation("Chablis");
			// List<ProductVintage> list=dao.findByVintage(2002);
			// List<ProductVarietal> list = dao.findByVariatal("Gamay");
			List<ProductType> list = dao.findByType("Red Wines");
			for (ProductType product : list) {
				for (Product p : product.getProductsWine()) {
					log.info("££££££££$$$$$$$$$$$$$$$$$" + p.getIdProduct() + " $$" + p.getName() + "%%%%%%%%%%");
				}

			}
		} catch (Exception e) {
			log.fatal(e);
		}
	}

}
