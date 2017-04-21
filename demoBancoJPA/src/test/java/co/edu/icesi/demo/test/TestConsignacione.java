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

import co.edu.icesi.demo.modelo.Consignacione;
import co.edu.icesi.demo.modelo.ConsignacionePK;
import co.edu.icesi.demo.modelo.Cuenta;

public class TestConsignacione {

	private static final Logger log = LoggerFactory.getLogger(TestConsignacione.class);

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	private ConsignacionePK consignacionePK = new ConsignacionePK();

	// ANTES
	@Before
	public void antes() {
		entityManagerFactory = Persistence.createEntityManagerFactory("demoBancoJPA");
		entityManager = entityManagerFactory.createEntityManager();
		consignacionePK.setConCodigo(5443L);
		consignacionePK.setCueNumero("4008-5305-0010");
	}

	// DESPUES
	@After
	public void despues() {
		entityManager.close();
		entityManagerFactory.close();
	}

	private String cueNumero = "4008-5305-0010";

	// CREAR
	@Test
	public void atest() {

		Consignacione consignacione = new Consignacione();
		consignacione.setId(consignacionePK);
		consignacione.setConDescripcion("Consignacion Superpoderosa");
		consignacione.setConFecha(new Timestamp(1));
		consignacione.setConValor(new BigDecimal(9812));

		Cuenta cuenta = (Cuenta) entityManager.find(Cuenta.class, cueNumero);
		consignacione.setCuenta(cuenta);

		entityManager.getTransaction().begin();
		entityManager.persist(consignacione);
		entityManager.getTransaction().commit();
	}

	// CONSULTAR
	@Test
	public void btest() {
		Consignacione consignacione = entityManager.find(Consignacione.class, consignacionePK);
		assertNotNull("La consignacion no existe", consignacione);

		log.info("" + consignacione.getConFecha());
		log.info("" + consignacione.getConValor());
		log.info(consignacione.getConDescripcion());

		log.debug(consignacione.getCuenta().getCueNumero());
	}

	// MODIFICAR
	@Test
	public void ctest() {

		Consignacione consignacione = entityManager.find(Consignacione.class, consignacionePK);

		consignacione.setConDescripcion("Consignacion Batman");
		consignacione.setConFecha(new Timestamp(1));
		consignacione.setConValor(new BigDecimal(9877));

		Cuenta cuenta = (Cuenta) entityManager.find(Cuenta.class, cueNumero);
		consignacione.setCuenta(cuenta);

		entityManager.getTransaction().begin();
		entityManager.merge(consignacione);
		entityManager.getTransaction().commit();
	}

	// BORRAR
	@Test
	public void dtest() {
		Consignacione consignacione = entityManager.find(Consignacione.class, consignacionePK);

		entityManager.getTransaction().begin();
		entityManager.remove(consignacione);
		entityManager.getTransaction().commit();
	}

}
