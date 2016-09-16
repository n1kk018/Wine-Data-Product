package fr.afcepf.atod.wine.data.product.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.atod.wine.data.product.api.IDaoCity;
import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.data.product.api.IDaoProductType;
import fr.afcepf.atod.wine.data.product.impl.DaoCity;
import fr.afcepf.atod.wine.entity.City;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;
import fr.afcepf.atod.wine.entity.ProductVarietal;


public class daoTester {
	private static Logger log = Logger.getLogger(daoTester.class);

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
			IDaoProduct dao = (IDaoProduct) bf.getBean(IDaoProduct.class);
			fr.afcepf.atod.wine.data.product.api.IDaoCity daoC=(IDaoCity) bf.getBean(IDaoCity.class);

			List<Product> list =null;
			list =dao.findByVarietalAndType("White Wines", "Rosé");
			for (Product product : list) {
				log.info("@@@@@@@@@@@@@@@ " + product.getName()+"@@@@@@@@à" +product.getPrice());
			}
//			List<City> lc =null;
//			lc =daoC.findAllObj();
//			for (City city : lc) {
//				log.info("gtgtgtgt" + city.getName());
//			}
//			IDaoProductType daoType = (IDaoProductType) bf.getBean(IDaoProductType.class);
//			/*Product p1 = dao.findObj(9);
//			log.info(p1);
//			Product p2 = dao.findByName("Maquis Cabernet Sauvignon 2012");
//			log.info(p2);*/
//			//List<Product> list= dao.findAllObj();
//			ProductType pt = daoType.findObj(1);
//			List<ProductVarietal> list = dao.getVarietalsByWineType(pt);
//			log.info(list);
//			for (ProductVarietal product : list) {
//				log.info(product.getDescription());
//			}
//			/*
//			 * Product p1 = dao.findObj(9); log.info(p1); Product p2 =
//			 * dao.findByName("Maquis Cabernet Sauvignon 2012"); log.info(p2);
//			 */
//			// List<Product> list= dao.findAllObj();
//			// List<Product> list = dao.getPromotedProductsSortedByEndDate(10);
//			// for (Product product : list) {
//			// log.info(product);
//			// }
//			Product proTest = dao.findByName("Chateau du Cedre Cahors Le Cedre 2011");
//			log.info("#################" + proTest.getIdProduct() + " " + proTest.getName() + "##############");
//
//			// List<Product> list=dao.findByNotCompleteName("Jean");
//			// List<Product> list=dao.findByAppelation("Chablis");
//			// List<ProductVintage> list=dao.findByVintage(2002);
//			 List<ProductVarietal> List = dao.findByVariatal("Gamay");
////			List<ProductType> list = dao.findByType("Red Wines");
//			for (ProductVarietal Product : List) {
//				for (Product p : Product.getProductsWine()) {
//					log.info("££££££££$$$$$$$$$$$$$$$$$" + p.getIdProduct() + " $$" + p.getName() + "%%%%%%%%%%");
//				}
//			}
			
		} catch (Exception e) {
			log.fatal(e);
		}
	}

}
