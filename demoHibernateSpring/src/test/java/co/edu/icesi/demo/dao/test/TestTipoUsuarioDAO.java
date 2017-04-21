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

import co.edu.icesi.demo.dao.ITiposUsuariosDAO;
import co.edu.icesi.demo.modelo.TiposUsuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTipoUsuarioDAO {

	private static final Logger log = LoggerFactory.getLogger(TestTipoUsuarioDAO.class);

	@Autowired
	private ITiposUsuariosDAO tusuariosDAO;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void aCreartipoUsuario() throws Exception {
		TiposUsuarios tipoUsuario = tusuariosDAO.consultarPorId(123456L);

		assertNull("El tipo usuario ya existe", tipoUsuario);

		tipoUsuario = new TiposUsuarios();
		tipoUsuario.setTusuCodigo(123456L);
		tipoUsuario.setTusuNombre("Nombre2");

		tusuariosDAO.grabar(tipoUsuario);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void bConsultarTipo() throws Exception {
		TiposUsuarios tipoUsuarios = tusuariosDAO.consultarPorId(123456L);
		assertNotNull("El tipo usuario no existe", tipoUsuarios);
		log.info(tipoUsuarios.getTusuNombre());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void cModificarTipo() throws Exception {
		TiposUsuarios tipoUsuarios = tusuariosDAO.consultarPorId(123456L);
		assertNotNull("El tipo usuario no existe", tipoUsuarios);
		tipoUsuarios.setTusuNombre("NuevoNombre");
		tusuariosDAO.modificar(tipoUsuarios);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void dBorrarUsuario() throws Exception {
		TiposUsuarios tipoUsuarios = tusuariosDAO.consultarPorId(123456L);
		assertNotNull("El tipo usuario no existe", tipoUsuarios);
		tusuariosDAO.borrar(tipoUsuarios);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void eConsultarTodosTipos() throws Exception {
		List<TiposUsuarios> losTipos = tusuariosDAO.consultarTodos();
		for (TiposUsuarios tiposUsu : losTipos) {
			log.info("" + tiposUsu.getTusuCodigo() + "");
			log.info(tiposUsu.getTusuNombre());
		}

	}

}
