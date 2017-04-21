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

import co.edu.icesi.demo.modelo.TiposUsuario;
import co.edu.icesi.demo.modelo.Usuario;

public class TestUsuario {

	private static final Logger log = LoggerFactory.getLogger(TestUsuario.class);

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;

	private long usuCedula = 974567L;

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

//CREAR
	@Test
	public void aTest() {

		Usuario usuario = new Usuario();
		usuario.setUsuCedula(usuCedula);
		usuario.setUsuClave("12345");
		usuario.setUsuLogin("LOGIN");
		usuario.setUsuNombre("CARLITOS");

		TiposUsuario tiposUsuario = (TiposUsuario) entityManager.find(TiposUsuario.class, 30L);
		usuario.setTiposUsuario(tiposUsuario);

		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}

	//CONSULTAR
	@Test
	public void bTest() {

		Usuario usuario = entityManager.find(Usuario.class, usuCedula);
		assertNotNull("El usuario no existe", usuario);

		log.info("" + usuario.getUsuCedula());
		log.info(usuario.getUsuNombre());
		log.info(usuario.getUsuLogin());

	}
	
	//MODIFICAR
	@Test
	public void cTest() {

		Usuario usuario = entityManager.find(Usuario.class, usuCedula);

		usuario.setUsuClave("12345678");
		usuario.setUsuLogin("Login2");
		usuario.setUsuNombre("JOSE");

		TiposUsuario tiposUsuario = (TiposUsuario) entityManager.find(TiposUsuario.class, 30L);
		usuario.setTiposUsuario(tiposUsuario);

		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
	}

	//ELIMINAR
	@Test
	public void dTest() {
		Usuario usuario = entityManager.find(Usuario.class, usuCedula);

		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
	}
	
}
