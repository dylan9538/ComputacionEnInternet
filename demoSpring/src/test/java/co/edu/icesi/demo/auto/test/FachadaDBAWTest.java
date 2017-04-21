package co.edu.icesi.demo.auto.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.demo.fachada.FachadaDB;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContextAW.xml")
public class FachadaDBAWTest {

	private static Logger log=LoggerFactory.getLogger(FachadaDBAWTest.class);

	@Autowired
	private FachadaDB fachadaDB;
	
	@Test
	public void test() {
	log.info(fachadaDB.getConexion().getUser());	
	}

}
