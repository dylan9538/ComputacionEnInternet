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
import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Cuentas;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCuentasDAO {

	private static final Logger log = LoggerFactory.getLogger(TestCuentasDAO.class);

	@Autowired
	private ICuentasDAO cuentasDAO;
	@Autowired
	private IClienteDAO clientesDAO;
	
	private String numero = "4008-5305-0085";

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void aCrearCuenta() throws Exception {
		Cuentas cuentas = cuentasDAO.consultarPorNumero(numero);

		assertNull("La cuenta ya existe", cuentas);

		cuentas = new Cuentas();
		Clientes clientes = clientesDAO.consultarPorId(111234L);
		cuentas.setClientes(clientes);
		cuentas.setCueActiva("S");
		cuentas.setCueClave("1234");
		cuentas.setCueNumero(numero);
		cuentas.setCueSaldo(new BigDecimal("100000.00"));
		

		cuentasDAO.grabar(cuentas);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void bConsultarCuenta() throws Exception {
		Cuentas cuentas = cuentasDAO.consultarPorNumero(numero);
		assertNotNull("La cuenta no existe", cuentas);
		log.info(cuentas.getCueNumero());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void cModificarCuenta() throws Exception {
		Cuentas cuentas = cuentasDAO.consultarPorNumero(numero);
		assertNotNull("La cuenta no existe", cuentas);
		cuentas.setCueActiva("N");
		Clientes clientes = clientesDAO.consultarPorId(111234);
		cuentas.setClientes(clientes);
		
		cuentasDAO.modificar(cuentas);
		
		
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void dBorrarCuenta() throws Exception {
		Cuentas cuentas = cuentasDAO.consultarPorNumero(numero);
		assertNotNull("La cuenta no existe", cuentas);
		cuentasDAO.borrar(cuentas);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void eConsultarTodasCcuentas() throws Exception {
		List<Cuentas> lasCuentas = cuentasDAO.consultarTodas();
		for (Cuentas cuentas : lasCuentas) {
			log.info(cuentas.getCueNumero());

		}

	}

}
