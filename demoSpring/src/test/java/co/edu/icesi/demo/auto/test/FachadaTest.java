package co.edu.icesi.demo.auto.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.edu.icesi.demo.fachada.FachadaDB;

public class FachadaTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContextAW.xml"});
      FachadaDB fachadaDB  = (FachadaDB) context.getBean("fachadaDB");
		
		context.close();
	}

}
