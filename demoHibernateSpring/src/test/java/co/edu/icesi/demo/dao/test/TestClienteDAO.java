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

import co.edu.icesi.demo.dao.IClienteDAO;
import co.edu.icesi.demo.dao.ITipoDocumentoDAO;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClienteDAO {

	private static final Logger log = LoggerFactory.getLogger(TestClienteDAO.class);

	@Autowired
	private IClienteDAO clienteDAO;
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	private long cliId = 16660L;
	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void aCrearCliente() throws Exception {
		Clientes cliente = clienteDAO.consultarPorId(cliId);

		assertNull("El cliente ya existe", cliente);

		cliente = new Clientes();
		cliente.setCliId(cliId);
		cliente.setCliDireccion("Avenida Siempre Viva 123");
		cliente.setCliMail("hjsimpson@gmail.com");
		cliente.setCliTelefono("3709090");
		cliente.setCliNombre("Homero J Simpson");
		
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(10L);
		cliente.setTiposDocumentos(tiposDocumentos);
		
		clienteDAO.grabar(cliente);
	}

	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void bConsultarCliente() throws Exception{
		Clientes cliente = clienteDAO.consultarPorId(cliId);
		assertNotNull("El cliente no existe", cliente);
		log.info(cliente.getCliNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void cModificarCliente() throws Exception{
		Clientes cliente = clienteDAO.consultarPorId(cliId);

		assertNotNull("El cliente no existe", cliente);
		cliente.setCliNombre("Homero J Simpson Cali");
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(20L);
		cliente.setTiposDocumentos(tiposDocumentos);
		
		clienteDAO.modificar(cliente);
	}
	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void dBorrarCliente() throws Exception{
		Clientes cliente = clienteDAO.consultarPorId(cliId);

		assertNotNull("El cliente no existe", cliente);
		clienteDAO.borrar(cliente);
	}
	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void eConsultarTodosCliente() throws Exception{
		List<Clientes> losClientes = clienteDAO.consultarTodos();
        for (Clientes clientes : losClientes) {
        	log.info(clientes.getCliNombre());
        	log.info(clientes.getCliTelefono());
			
		}
		
	}

}
