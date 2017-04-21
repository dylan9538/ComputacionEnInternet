package co.edu.icesi.demo.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
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

import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUsuariosDAO {

	public static final Logger log = LoggerFactory.getLogger(TestUsuariosDAO.class);

	@Autowired
	private IUsuariosDAO usuariosDAO;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void aCrearUsuario() throws Exception {
		Usuarios usuarios = usuariosDAO.consultarPorCedula(35L);

		assertNull("El usurio ya existe", usuarios);

		usuarios = new Usuarios();
		usuarios.setUsuCedula(35L);
		usuarios.setUsuClave("1234");
		usuarios.setUsuLogin("10");
		usuarios.setUsuNombre("CAMILA RODRIGUEZ");

		usuariosDAO.grabar(usuarios);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void bConsultarUsuario() throws Exception {
		Usuarios usuarios = usuariosDAO.consultarPorCedula(35L);
		assertNotNull("El usuario no existe", usuarios);
		log.info(usuarios.getUsuNombre());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void cModificarUsuario() throws Exception {
		Usuarios usuarios = usuariosDAO.consultarPorCedula(35L);
		assertNotNull("El usuario existe", usuarios);
		usuarios.setUsuClave("2345");
		usuarios.setUsuLogin("Hola");
		usuariosDAO.modificar(usuarios);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void dBorrarUsuario() throws Exception {
		Usuarios usuarios = usuariosDAO.consultarPorCedula(35L);
		assertNotNull("El cliente no existe", usuarios);
		usuariosDAO.borrar(usuarios);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void eConsultarTodasCUsuarios() throws Exception {
		List<Usuarios> losUsuarios = usuariosDAO.consultarTodos();
		for (Usuarios usuarios : losUsuarios) {
			log.info(usuarios.getUsuNombre() + "/n");

		}

	}

}
