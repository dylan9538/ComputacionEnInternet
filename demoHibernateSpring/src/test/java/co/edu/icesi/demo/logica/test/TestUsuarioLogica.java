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
import co.edu.icesi.demo.logica.IUsuarioLogica;
import co.edu.icesi.demo.modelo.TiposUsuarios;
import co.edu.icesi.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUsuarioLogica {

	private static final Logger log = LoggerFactory.getLogger(TestUsuarioLogica.class);

	@Autowired
	private IUsuarioLogica usuarioLogica;

	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;

	private long usuId = 80L;

	@Test
	public void ATestCrear() throws Exception {
		try {
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(20L);
			Usuarios usuarios = new Usuarios(usuId, tiposUsuarios, "CARLOS PEREZ", "1234", "5678",null, null);
			usuarioLogica.grabar(usuarios);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void bTestConsultar() throws Exception {
		Usuarios usuarios =  usuarioLogica.consultarPorId(usuId);
		assertNotNull("El usuario no existe", usuarios);
		log.info(usuarios.getUsuNombre());
	}
	
	@Test
	public void cTestModificar() throws Exception {
		Usuarios usuarios =  usuarioLogica.consultarPorId(usuId);
		assertNotNull("El usuario no existe", usuarios);
		usuarios.setUsuNombre("MARLON");
		
		try {
			usuarioLogica.modificar(usuarios);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	@Test
	public void dTestBorrar() throws Exception {
		Usuarios usuarios =  usuarioLogica.consultarPorId(usuId);
		assertNotNull("El usuario no existe", usuarios);
		try {
			usuarioLogica.borrar(usuarios);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	@Test
	public void eTestConsultarTodos() throws Exception {
		List<Usuarios> losUsuarios = usuarioLogica.consultarTodos();
		for (Usuarios usuarios : losUsuarios) {
			log.info(usuarios.getUsuNombre());
		}
	}
}
