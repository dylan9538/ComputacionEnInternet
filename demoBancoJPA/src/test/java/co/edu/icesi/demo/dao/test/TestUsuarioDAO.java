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
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.TiposUsuario;
import co.edu.icesi.demo.modelo.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUsuarioDAO {

	
	private static final Logger log = LoggerFactory.getLogger(TestUsuarioDAO.class);

	@Autowired
	private IUsuariosDAO usuarioDAO;
	
	@Autowired
	private ITipoDeUsuarioDAO tipoUsuarioDAO;
	
	private long ced = 98765L;
	
	
	//CrearTipoUsuario
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void aTest() throws Exception{
				
				
				
				Usuario usuarios = usuarioDAO.consultarPorCedula(ced);
				
				assertNull("el usuario ya existe", usuarios);
				
				usuarios = new Usuario();
				usuarios.setUsuCedula(ced);	
				usuarios.setUsuClave("12345");
				usuarios.setUsuLogin("DavidPdp");
				usuarios.setUsuNombre("David");
				
				TiposUsuario tipoUsuarios = tipoUsuarioDAO.consultarPorCodigo(10L);
				usuarios.setTiposUsuario(tipoUsuarios);
				
				usuarioDAO.grabar(usuarios);
				
			}
			
			
			//Consulta
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void bTest() throws Exception{
				
				Usuario usuarios = usuarioDAO.consultarPorCedula(ced);

				
				assertNotNull("el usuario no existe", usuarios);
				
				log.info(usuarios.getUsuNombre());
				
						
			}
			
			//Modificar
			
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void cTest() throws Exception{
				
				Usuario usuarios = usuarioDAO.consultarPorCedula(ced);

				
				assertNotNull("el usuario no existe", usuarios);
				
				usuarios.setUsuNombre("Flaco");
			
				
				usuarioDAO.modificar(usuarios);
				
						
			}
			
			//Borrar
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void dTest() throws Exception{
				
				Usuario usuarios = usuarioDAO.consultarPorCedula(ced);

				
				assertNotNull("el usuario no existe", usuarios);

				
				usuarioDAO.borrar(usuarios);
				
						
			}
			
			
			//Consultar Todos
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void eTest() throws Exception{
				
				List<Usuario> usuarios = usuarioDAO.consultarTodos();
				for(Usuario usua: usuarios){
					log.info("Cedula: "+usua.getUsuCedula());
					log.info("Nombre: "+ usua.getUsuNombre());

				}						
			}
			

}
