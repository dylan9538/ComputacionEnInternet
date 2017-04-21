package co.edu.icesi.demo.spring.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.demo.modelo.Clientes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestCliente {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger log = LoggerFactory.getLogger(TestCliente.class);
	
	@Test
	public void aConsultarCliente() {

		Session session = sessionFactory.openSession();
		String hql = "SELECT c FROM Clientes c";
		Query query = session.createQuery(hql);
		List<Clientes> losClientes = query.list();

		for (Clientes clientes : losClientes) {
			log.info("" + clientes.getCliId());
			log.info(clientes.getCliNombre());
			log.info(clientes.getCliTelefono());
		}
		session.close();
	}

}
