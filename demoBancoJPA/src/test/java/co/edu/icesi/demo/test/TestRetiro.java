package co.edu.icesi.demo.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.modelo.Retiro;
import co.edu.icesi.demo.modelo.Cuenta;
import co.edu.icesi.demo.modelo.RetiroPK;

public class TestRetiro {

	private static final Logger log = LoggerFactory.getLogger(TestCliente.class);

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	private String cueNumero = "4008-5305-0010";
	private RetiroPK IdRetiro = new RetiroPK();

//ANTES
	@Before
	public void antes() {
		entityManagerFactory = Persistence.createEntityManagerFactory("demoBancoJPA");
		entityManager = entityManagerFactory.createEntityManager();
		IdRetiro.setRetCodigo(356L);
		IdRetiro.setCueNumero("4008-5305-0010");
	}

//DESPUES
	@After
	public void despues() {
		entityManager.close();
		entityManagerFactory.close();
	}

	// CREAR
	@Test
	public void atest() {
		Retiro retiro = new Retiro();
		retiro.setId(IdRetiro);
		retiro.setRetDescripcion("RETIRO NUEVO");
		retiro.setRetFecha(new Timestamp(10000));
		retiro.setRetValor(new BigDecimal(9999));
		Cuenta cuenta = (Cuenta) entityManager.find(Cuenta.class, cueNumero);
		retiro.setCuenta(cuenta);
		entityManager.getTransaction().begin();
		entityManager.persist(retiro);
		entityManager.getTransaction().commit();
	}

	// CONSULTAR
	@Test
	public void btest() {
		Retiro retiro = entityManager.find(Retiro.class, IdRetiro);
		assertNotNull("El retiro no existe", retiro);
		log.info(retiro.getRetFecha() + "");
		log.info(retiro.getRetValor() + "");
		log.info(retiro.getRetDescripcion());
		log.debug(retiro.getCuenta().getCueNumero());
	}

	// MODIFICAR
	@Test
	public void ctest() {

		Retiro retiro = entityManager.find(Retiro.class, IdRetiro);
		retiro.setRetDescripcion("RETIRO NUEVO2");
		retiro.setRetFecha(new Timestamp(1));
		retiro.setRetValor(new BigDecimal(5050));
		Cuenta cuenta = (Cuenta) entityManager.find(Cuenta.class, cueNumero);
		retiro.setCuenta(cuenta);
		entityManager.getTransaction().begin();
		entityManager.merge(retiro);
		entityManager.getTransaction().commit();
	}

	// BORRAR
	@Test
	public void dtest() {
		Retiro retiro = entityManager.find(Retiro.class, IdRetiro);
		entityManager.getTransaction().begin();
		entityManager.remove(retiro);
		entityManager.getTransaction().commit();
	}

}
