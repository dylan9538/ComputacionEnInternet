package co.edu.icesi.demo.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.modelo.Cliente;
import co.edu.icesi.demo.modelo.Cuenta;

public class TestCuenta {

	private static final Logger log = LoggerFactory.getLogger(TestCuenta.class);

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	private String cueNumero = "4008-5305-0090";

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

	// CREAR

	@Test
	public void aTest() {

		Cuenta cuenta = new Cuenta();
		cuenta.setCueNumero(cueNumero);
		cuenta.setCueActiva("S");
		cuenta.setCueClave("159746");
		cuenta.setCueSaldo(new BigDecimal(10000000));

		Cliente cliente = (Cliente) entityManager.find(Cliente.class, 801234L);
		cuenta.setCliente(cliente);

		entityManager.getTransaction().begin();
		entityManager.persist(cuenta);
		entityManager.getTransaction().commit();
	}

	// CONSULTAR
	@Test
	public void bTest() {
		Cuenta cuenta = entityManager.find(Cuenta.class, cueNumero);
		assertNotNull("La cuenta no existe", cuenta);

		log.info("" + cuenta.getCueNumero());
		log.info(cuenta.getCueSaldo() + "");
		log.info(cuenta.getCueActiva());

		log.debug(cuenta.getCliente().getCliNombre());

	}

	// MODIFICAR
	@Test
	public void cTest() {

		Cuenta cuenta = entityManager.find(Cuenta.class, cueNumero);

		cuenta.setCueActiva("N");
		cuenta.setCueClave("74595");
		cuenta.setCueSaldo(new BigDecimal(80000000));

		Cliente cliente = (Cliente) entityManager.find(Cliente.class, 801234L);
		cuenta.setCliente(cliente);

		entityManager.getTransaction().begin();
		entityManager.merge(cuenta);
		entityManager.getTransaction().commit();
	}

	// ELIMINAR
	@Test
	public void dTest() {
		Cuenta cuenta = entityManager.find(Cuenta.class, cueNumero);
		entityManager.getTransaction().begin();
		entityManager.remove(cuenta);
		entityManager.getTransaction().commit();
	}

}
