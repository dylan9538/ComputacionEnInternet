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

import co.edu.icesi.demo.logica.IClienteLogica;
import co.edu.icesi.demo.logica.ITipoDocumentoLogica;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClienteLogica {

	private static final Logger log = LoggerFactory.getLogger(TestTipoDocumentoLogica.class);

	@Autowired
	private IClienteLogica clienteLogica;

	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;

	private long cliId = 16636807L;

	@Test
	public void ATestCrear() throws Exception {
		try {
			TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(10L);
			Clientes clientes = new Clientes(cliId, tiposDocumentos, "Dylan Torres", "Car 2b 71-19", "444444", "diland.t95@hotmail.com", null);
			clienteLogica.grabar(clientes);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void bTestConsultar() throws Exception {
		Clientes clientes = clienteLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		log.info(clientes.getCliNombre());
	}
	
	@Test
	public void cTestModificar() throws Exception {
		Clientes clientes = clienteLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		clientes.setCliNombre("nuevoNombre");
		
		try {
			clienteLogica.modificar(clientes);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void dTestBorrar() throws Exception {
		Clientes clientes = clienteLogica.consultarPorId(cliId);
		assertNotNull("El cliente no existe", clientes);
		try {
			clienteLogica.borrar(clientes);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
	}
	
	@Test
	public void eTestConsultarTodos() throws Exception {
		List<Clientes> losClientes = clienteLogica.consultarTodos();
		for (Clientes clientes : losClientes) {
			log.info(clientes.getCliNombre());
		}
		
	}

}
