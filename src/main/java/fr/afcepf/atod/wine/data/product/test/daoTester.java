package fr.afcepf.atod.wine.data.product.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.afcepf.atod.wine.data.product.api.IDaoProduct;
import fr.afcepf.atod.wine.data.product.api.IDaoProductType;
import fr.afcepf.atod.wine.entity.Product;
import fr.afcepf.atod.wine.entity.ProductType;

public class daoTester {
	 private static Logger log = Logger.getLogger(daoTester.class);

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			BeanFactory bf = new ClassPathXmlApplicationContext("classpath:springData.xml");
			IDaoProduct dao = (IDaoProduct) bf.getBean(IDaoProduct.class);
			IDaoProductType daoType = (IDaoProductType) bf.getBean(IDaoProductType.class);
			/*Product p1 = dao.findObj(9);
			log.info(p1);
			Product p2 = dao.findByName("Maquis Cabernet Sauvignon 2012");
			log.info(p2);*/
			//List<Product> list= dao.findAllObj();
			ProductType pt = daoType.findObj(1);
			List<String> list = dao.getAppellationsByWineType(pt);
			for (String product : list) {
				log.info(product);
			}
		} catch (Exception e) {
			log.fatal(e);
		}
	}

}
