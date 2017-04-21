package co.edu.icesi.demo.logica.test;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import co.edu.icesi.demo.logica.IClienteLogica;
import co.edu.icesi.demo.logica.ICuentasLogica;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Cuentas;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCuentaLogica {

	private static final Logger log = LoggerFactory.getLogger(TestCuentaLogica.class);

	@Autowired
	private ICuentasLogica cuentaLogica;
	@Autowired
	private IClienteLogica clienteLogica;
	
	private String numero = "4008-5305-0085";

	@Test
	public void ATestCrear() throws Exception {
		try {
			Clientes clientes = clienteLogica.consultarPorId(101234L);
			Cuentas cuentas = new Cuentas(numero, clientes, new BigDecimal(100000.00), "S", "1234", null, null);
			cuentaLogica.grabar(cuentas);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Test
	public void bTestConsultar() throws Exception {
		Cuentas cuentas = cuentaLogica.consultarPorNumero(numero);
		assertNotNull("La cuenta no existe", cuentas);
		log.info(cuentas.getCueNumero());
	}

	@Test
	public void cTestModificar() throws Exception {
		Cuentas cuentas = cuentaLogica.consultarPorNumero(numero);
		assertNotNull("La cuenta no existe", cuentas);
		cuentas.setCueActiva("N");

		try {
			cuentaLogica.modificar(cuentas);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Test
	public void dTestBorrar() throws Exception {
		Cuentas cuentas = cuentaLogica.consultarPorNumero(numero);
		assertNotNull("La cuenta no existe", cuentas);
		try {
			cuentaLogica.borrar(cuentas);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Test
	public void eTestConsultarTodos() throws Exception {
		List<Cuentas> lasCuentas = cuentaLogica.consultarTodos();
		for (Cuentas cuentas : lasCuentas) {
			log.info(cuentas.getCueNumero());
		}
	}
}
