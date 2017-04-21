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

import co.edu.icesi.demo.dao.IClienteDAO;
import co.edu.icesi.demo.dao.ICuentaDAO;
import co.edu.icesi.demo.modelo.Cliente;
import co.edu.icesi.demo.modelo.Cuenta;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCuentasDAO {

	private static final Logger log = LoggerFactory.getLogger(TestClienteDAO.class);
	
	@Autowired
	private ICuentaDAO cuentaDAO;
	
	@Autowired
	private IClienteDAO clienteDAO;

	private String cueNum = "100";
	//CrearCuenta
	@Test
	@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void aTest() throws Exception{
		
		Cuenta Cuenta = cuentaDAO.consultarPorNumero(cueNum);
		
		assertNull("La cuenta ya existe", Cuenta);

		Cuenta= new Cuenta();

		
		Cuenta.setCueActiva("S");
		Cuenta.setCueClave("9873547");
		Cuenta.setCueNumero(cueNum);
		Cuenta.setCueSaldo(new BigDecimal(10000000));
		
		Cliente cliente = clienteDAO.consultarPorID(101234L);
		Cuenta.setCliente(cliente);
		cuentaDAO.grabar(Cuenta);
		
	}
	
	
	//Consulta
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void bTest() throws Exception{
			
			Cuenta Cuenta = cuentaDAO.consultarPorNumero(cueNum);
			
			assertNotNull("la cuenta no existe", Cuenta);
			
			log.info(Cuenta.getCueClave());
			
					
		}
		
		//Modificar
		
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void cTest() throws Exception{
			
			Cuenta Cuenta = cuentaDAO.consultarPorNumero(cueNum);
			
			assertNotNull("la cuenta no existe", Cuenta);
			
			Cuenta.setCueClave("1234");
		
			
			cuentaDAO.modificar(Cuenta);
			
					
		}
		
		//Borrar
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void dTest() throws Exception{
			
			Cuenta Cuenta = cuentaDAO.consultarPorNumero(cueNum);
			
			assertNotNull("la cuenta existe", Cuenta);

			
			cuentaDAO.borrar(Cuenta);
			
					
		}
		
		//Consultar Todos
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void eTest() throws Exception{
			
			List<Cuenta> lasCuenta = cuentaDAO.consultarTodos();
			for(Cuenta Cuenta: lasCuenta){
				log.info("Cuenta Activa: "+Cuenta.getCueActiva());
				log.info("Clave: "+ Cuenta.getCueClave());

			}
			
			
					
		}

}
