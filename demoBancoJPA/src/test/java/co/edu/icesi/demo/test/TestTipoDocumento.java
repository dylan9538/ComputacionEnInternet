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

import co.edu.icesi.demo.modelo.TiposDocumento;

public class TestTipoDocumento {

	private static final Logger log = LoggerFactory.getLogger(TestTipoDocumento.class);

	EntityManagerFactory entityManagerFactory = null; 
	EntityManager entityManager=null;
	
	private Long tdocCodigo= 3L;

	
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
	
	//Crear
	@Test
	public void atest() {

		TiposDocumento tiposDocumento = new TiposDocumento();
		tiposDocumento.setTdocCodigo(tdocCodigo);
		tiposDocumento.setTdocNombre("NUEVO DOCUMENTO");
		
		entityManager.getTransaction().begin();
		entityManager.persist(tiposDocumento);
		entityManager.getTransaction().commit();
	}
	

	
	//Consultar
		@Test
		public void btest() {


			
			TiposDocumento tiposDocumento = entityManager.find(TiposDocumento.class, tdocCodigo);
			assertNotNull("El tipo de documento no existe", tiposDocumento);

			log.info(""+tiposDocumento.getTdocCodigo());
			log.info(tiposDocumento.getTdocNombre());


		}
		

		
		//modificar
		@Test
		public void ctest(){

			TiposDocumento tiposDocumento = entityManager.find(TiposDocumento.class, tdocCodigo);
			
			tiposDocumento.setTdocCodigo(tdocCodigo);
			tiposDocumento.setTdocNombre("NEW DOCUMENT");
			
			entityManager.getTransaction().begin();
			entityManager.merge(tiposDocumento);
			entityManager.getTransaction().commit();
		}
		
		@Test
		public void dtest(){
			TiposDocumento tiposDocumento = entityManager.find(TiposDocumento.class, tdocCodigo);

			entityManager.getTransaction().begin();
			entityManager.remove(tiposDocumento);
			entityManager.getTransaction().commit();
		}
	

}
