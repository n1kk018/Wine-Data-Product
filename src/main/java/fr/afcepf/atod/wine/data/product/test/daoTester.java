package fr.afcepf.atod.wine.data.product.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.entity.Product;

public class daoTester {
	 private static Logger log = Logger.getLogger(daoTester.class);

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
			IDaoProduct dao = (IDaoProduct) bf.getBean(IDaoProduct.class);
			/*Product p1 = dao.findObj(9);
			log.info(p1);
			Product p2 = dao.findByName("Maquis Cabernet Sauvignon 2012");
			log.info(p2);*/
			//List<Product> list= dao.findAllObj();
			List<Product> list = dao.getPromotedProductsSortedByEndDate(10);
			for (Product product : list) {
				log.info(product);
			}
		} catch (Exception e) {
			log.fatal(e);
		}
	}

}
