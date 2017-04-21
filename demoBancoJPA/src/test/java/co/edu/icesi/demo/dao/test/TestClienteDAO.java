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
import co.edu.icesi.demo.modelo.Cliente;
import co.edu.icesi.demo.modelo.TiposDocumento;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClienteDAO {

	
	private static final Logger log = LoggerFactory.getLogger(TestClienteDAO.class);

	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	private Long cliId= 16660L;
	
	
	//CrearCliente
	@Test
	@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void aTest() throws Exception{
		
		Cliente clientes = clienteDAO.consultarPorID(cliId);
		
		assertNull("El cliente ya existe", clientes);
		
		clientes= new Cliente();
		clientes.setCliId(cliId);
		clientes.setCliDireccion("Avenida123");
		clientes.setCliMail("simpson@gmail.com");
		clientes.setCliTelefono("4444448");
		clientes.setCliNombre("Homero Simpson");
		TiposDocumento tiposDocumentos = tipoDocumentoDAO.consultarPorID(10L);
		clientes.setTiposDocumento(tiposDocumentos);
		
		clienteDAO.grabar(clientes);
		
	}
	//Consulta
	@Test
	@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void bTest() throws Exception{
		
		Cliente clientes = clienteDAO.consultarPorID(cliId);
		
		assertNotNull("El cliente no ya existe", clientes);
		
		log.info(clientes.getCliNombre());
		
				
	}
	
	//Modificar
	
	@Test
	@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void cTest() throws Exception{
		
		Cliente clientes = clienteDAO.consultarPorID(cliId);
		
		assertNotNull("El cliente no ya existe", clientes);
		
		clientes.setCliNombre("Homero J Simpson2");
		TiposDocumento tiposDocumentos = tipoDocumentoDAO.consultarPorID(20L);
		clientes.setTiposDocumento(tiposDocumentos);
		
		clienteDAO.modificar(clientes);
		
				
	}
	
	//Borrar
	@Test
	@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void dTest() throws Exception{
		
		Cliente clientes = clienteDAO.consultarPorID(cliId);
		
		assertNotNull("El cliente no ya existe", clientes);

		
		clienteDAO.borrar(clientes);
		
				
	}
	
	//Consultar Todos
	@Test
	@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void eTest() throws Exception{
		
		List<Cliente> losClientes = clienteDAO.consultarTodos();
		for(Cliente clientes: losClientes){
			log.info("Id: "+clientes.getCliId());
			log.info("Nombre: "+clientes.getCliNombre());

		}
		
		
				
	}

}
