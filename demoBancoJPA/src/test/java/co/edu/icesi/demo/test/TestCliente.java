package co.edu.icesi.demo.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.modelo.Cliente;
import co.edu.icesi.demo.modelo.TiposDocumento;

public class TestCliente {

	private static final Logger log = LoggerFactory.getLogger(TestCliente.class);

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	@Before
	public void antes() {
		entityManagerFactory = Persistence.createEntityManagerFactory("demoBancoJPA");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void despues() {
		entityManager.close();
		entityManagerFactory.close();
	}

	private long cliId = 142020L;

	// CREAR
	@Test
	public void atest() {

		Cliente clientes = new Cliente();
		clientes.setCliId(cliId);
		clientes.setCliDireccion("Avenida123");
		clientes.setCliMail("simpson@gmail.com");
		clientes.setCliTelefono("4444444");
		clientes.setCliNombre("Homero Simpson");

		TiposDocumento tiposDocumentos = (TiposDocumento) entityManager.find(TiposDocumento.class, 10L);
		clientes.setTiposDocumento(tiposDocumentos);

		entityManager.getTransaction().begin();
		entityManager.persist(clientes);
		entityManager.getTransaction().commit();
	}

	// CONSULTAR
	@Test
	public void btest() {
		Cliente clientes = entityManager.find(Cliente.class, cliId);
		assertNotNull("El cliente no existe", clientes);

		log.info("" + clientes.getCliId());
		log.info(clientes.getCliNombre());
		log.info(clientes.getCliDireccion());

		log.debug(clientes.getTiposDocumento().getTdocNombre());

	}

	// MODIFICAR
	@Test
	public void ctest() {

		Cliente clientes = entityManager.find(Cliente.class, cliId);

		clientes.setCliDireccion("Avenida123");
		clientes.setCliMail("simpson@gmail.com");
		clientes.setCliTelefono("4444448");
		clientes.setCliNombre("Homero Simpson");

		TiposDocumento tiposDocumentos = entityManager.find(TiposDocumento.class, 10L);
		clientes.setTiposDocumento(tiposDocumentos);

		entityManager.getTransaction().begin();
		entityManager.merge(clientes);
		entityManager.getTransaction().commit();
	}

	// BORRAR
	@Test
	public void dtest() {
		Cliente clientes = entityManager.find(Cliente.class, cliId);

		entityManager.getTransaction().begin();
		entityManager.remove(clientes);
		entityManager.getTransaction().commit();
	}

}
