package co.edu.icesi.demo.logica.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.demo.logica.ITipoUsuarioLogica;
import co.edu.icesi.demo.modelo.TiposUsuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestTipoUsuarioLogica {

	private static final Logger log = LoggerFactory.getLogger(TestTipoUsuarioLogica.class);

	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;

	private long tusuC = 14L;

	@Test
	public void aTestCrear() throws Exception {
		TiposUsuarios tiposUsuarios = new TiposUsuarios();
		tiposUsuarios.setTusuCodigo(tusuC);
		tiposUsuarios.setTusuNombre("NUEVO TIPO USUARIO");
		try {
			tipoUsuarioLogica.grabar(tiposUsuarios);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Test
	public void bTestConsultar() throws Exception {
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(tusuC);
		assertNotNull("El tipo de usurario no existe", tiposUsuarios);
		log.info(tiposUsuarios.getTusuNombre());
	}

	@Test
	public void cTestModificar() throws Exception {
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(tusuC);
		assertNotNull("El tipo de usurario no existe", tiposUsuarios);
		tiposUsuarios.setTusuNombre("NUEVO NOMBRE TIPO");

		try {
			tipoUsuarioLogica.modificar(tiposUsuarios);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Test
	public void dTestBorrar() throws Exception {
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(tusuC);
		assertNotNull("El tipo de usurario no existe", tiposUsuarios);
		try {
			tipoUsuarioLogica.borrar(tiposUsuarios);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}

	@Test
	public void eTestConsultarTodos() throws Exception {
		List<TiposUsuarios> losTiposUsuarios = tipoUsuarioLogica.consultarTodos();
		for (TiposUsuarios tiposUsuarios: losTiposUsuarios) {
			log.info(tiposUsuarios.getTusuNombre());
		}

	}

}
