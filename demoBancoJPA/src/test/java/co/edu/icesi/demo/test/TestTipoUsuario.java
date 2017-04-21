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

public class TestTipoUsuario {

private static final Logger log = LoggerFactory.getLogger(TestTipoUsuario.class);
	
	EntityManagerFactory entityManagerFactory= null;
	EntityManager entityManager=null;
	
	private long tusuCodigo = 30L;

	
	@Before
	public void antes(){
		entityManagerFactory=Persistence.createEntityManagerFactory("demoBancoJPA");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@After
	public void despues(){
		entityManager.close();
		entityManagerFactory.close();
	}
	
	
	//CREAR
	@Test
	public void atest() {

		TiposUsuario tiposUsuario = new TiposUsuario();
		tiposUsuario.setTusuCodigo(tusuCodigo);
		tiposUsuario.setTusuNombre("NUEVO TIPO USUARIO");
		
		entityManager.getTransaction().begin();
		entityManager.persist(tiposUsuario);
		entityManager.getTransaction().commit();
	}
	
	//CONSULTAR
		@Test
		public void btest() {


			TiposUsuario tiposUsuario = entityManager.find(TiposUsuario.class, tusuCodigo);
			assertNotNull("El tipo usuario no existe", tiposUsuario);

			log.info(""+tiposUsuario.getTusuCodigo());
			log.info(tiposUsuario.getTusuNombre());

		}
		

		
		//MODIFICAR
		@Test
		public void ctest(){

			TiposUsuario tiposUsuario = entityManager.find(TiposUsuario.class, tusuCodigo);
			
			tiposUsuario.setTusuCodigo(tusuCodigo);
			tiposUsuario.setTusuNombre("Tipo Nuevo2");

			entityManager.getTransaction().begin();
			entityManager.merge(tiposUsuario);
			entityManager.getTransaction().commit();
		}
		
		@Test
		public void dtest(){
			TiposUsuario tiposUsuario = entityManager.find(TiposUsuario.class, tusuCodigo);

			entityManager.getTransaction().begin();
			entityManager.remove(tiposUsuario);
			entityManager.getTransaction().commit();
		}

}
