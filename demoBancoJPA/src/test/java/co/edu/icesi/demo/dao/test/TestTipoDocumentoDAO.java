package co.edu.icesi.demo.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITipoDocumentoDAO;
import co.edu.icesi.demo.modelo.TiposDocumento;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestTipoDocumentoDAO {
	
	private static final Logger log = LoggerFactory.getLogger(TestTipoDocumentoDAO.class);


	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;

	private Long tdocCodigo= 3L;

	
	//Crear
	@Test
	@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void aTest() {
		
		TiposDocumento tipoDocumentos = tipoDocumentoDAO.consultarPorID(tdocCodigo);
		
		assertNull("El tipo de documento ya existe", tipoDocumentos);
		
		tipoDocumentos = new TiposDocumento();
		
		tipoDocumentos.setTdocCodigo(tdocCodigo);
		tipoDocumentos.setTdocNombre("Nuevo");
		
		
		
		tipoDocumentoDAO.grabar(tipoDocumentos);
		
	}
	
	//Consulta
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void bTest() throws Exception{
			
			TiposDocumento TiposDocumento = tipoDocumentoDAO.consultarPorID(tdocCodigo);
			
			assertNotNull("El cliente no ya existe", TiposDocumento);
			
			log.info(TiposDocumento.getTdocNombre());
			
					
		}
		
		//Modificar
		
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void cTest() throws Exception{
			
			TiposDocumento TiposDocumento = tipoDocumentoDAO.consultarPorID(tdocCodigo);
			
			assertNotNull("El cliente no ya existe", TiposDocumento);
			
			TiposDocumento.setTdocNombre("Nuevo1");
			
			tipoDocumentoDAO.modificar(TiposDocumento);
			
					
		}
		
		//Borrar
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void dTestModificarCliente() throws Exception{
			
			TiposDocumento TiposDocumento = tipoDocumentoDAO.consultarPorID(tdocCodigo);
			
			assertNotNull("El cliente no ya existe", TiposDocumento);

			
			tipoDocumentoDAO.borrar(TiposDocumento);
			
					
		}
		
		//Consultar Todos
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void eTest() throws Exception{
			
			List<TiposDocumento> losDocumentos = tipoDocumentoDAO.consultarTodos();
			
			for(TiposDocumento documentos: losDocumentos){
				log.info("Id: "+documentos.getTdocCodigo());
				log.info("Nombre: "+documentos.getTdocNombre());

			}
			
			
					
		}

}
