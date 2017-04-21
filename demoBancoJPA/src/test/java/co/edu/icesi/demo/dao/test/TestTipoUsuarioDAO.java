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

import co.edu.icesi.demo.dao.ITipoDeUsuarioDAO;

import co.edu.icesi.demo.modelo.TiposUsuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTipoUsuarioDAO {

	private static final Logger log = LoggerFactory.getLogger(TestTipoUsuarioDAO.class);

	@Autowired
	private ITipoDeUsuarioDAO tipoUsuarioDAO;
	
	private long cod = 30L;
	//CrearTipoUsuario
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void aTest() throws Exception{
			
			
			
			TiposUsuario TiposUsuario = tipoUsuarioDAO.consultarPorCodigo(cod);
			
			assertNull("el tipo de usuario ya existe", TiposUsuario);
			
			TiposUsuario = new TiposUsuario();
			TiposUsuario.setTusuCodigo(cod);
			TiposUsuario.setTusuNombre("GERENTE");		
			
			tipoUsuarioDAO.grabar(TiposUsuario);
			
		}
		
		//Consulta
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void bTest() throws Exception{
			
			TiposUsuario TiposUsuario = tipoUsuarioDAO.consultarPorCodigo(cod);

			
			assertNotNull("el tipo de usuario no existe", TiposUsuario);
			
			log.info(TiposUsuario.getTusuNombre());
			
					
		}
		
		//Modificar
		
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void cTest() throws Exception{
			
			TiposUsuario TiposUsuario = tipoUsuarioDAO.consultarPorCodigo(cod);

			
			assertNotNull("el tipo de usuario no existe", TiposUsuario);
			
			TiposUsuario.setTusuNombre("ASESOR");
		
			
			tipoUsuarioDAO.modificar(TiposUsuario);
			
					
		}
		
		//Borrar
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void dTest() throws Exception{
			
			TiposUsuario TiposUsuario = tipoUsuarioDAO.consultarPorCodigo(cod);

			
			assertNotNull("el tipo de usuario no existe",  TiposUsuario);

			
			tipoUsuarioDAO.borrar(TiposUsuario);
			
					
		}
		
		
		//Consultar Todos
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void eTest() throws Exception{
			
			List<TiposUsuario> tiposDeUsuarios = tipoUsuarioDAO.consultarTodos();
			for(TiposUsuario tipos: tiposDeUsuarios){
				log.info("Codigo: "+tipos.getTusuCodigo());
				log.info("Nombre: "+ tipos.getTusuNombre());

			}
			
			
					
		}
		
		

}
