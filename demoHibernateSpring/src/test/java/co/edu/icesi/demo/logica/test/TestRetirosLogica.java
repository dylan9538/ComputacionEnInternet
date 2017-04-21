package co.edu.icesi.demo.logica.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
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

import co.edu.icesi.demo.dao.IConsignacionesDAO;
import co.edu.icesi.demo.logica.IClienteLogica;
import co.edu.icesi.demo.logica.IConsignacionesLogica;
import co.edu.icesi.demo.logica.ICuentasLogica;
import co.edu.icesi.demo.logica.IRestirosLogica;
import co.edu.icesi.demo.logica.IUsuarioLogica;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;
import co.edu.icesi.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRetirosLogica {

	private static final Logger log = LoggerFactory.getLogger(TestRetirosLogica.class);

	@Autowired
	private IRestirosLogica retirosLogica;
	@Autowired
	private ICuentasLogica cuentaLogica;
	@Autowired
	private IUsuarioLogica usuarioLogica;

	@Test
	public void ATestCrear() throws Exception {
		try {
			Usuarios usuarios = usuarioLogica.consultarPorId(25L);
			Cuentas cuentas = cuentaLogica.consultarPorNumero("4008-5305-0035");
			Retiros retiros = new Retiros(new RetirosId(16L, cuentas.getCueNumero()),
					usuarios, cuentas, new BigDecimal(10000000.00), new Date(), "conDescripcion");
			retirosLogica.grabar(retiros);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Test
	public void bTestConsultar() throws Exception {
		Retiros retiros  = retirosLogica
				.consultarPorId(new RetirosId(16L, "4008-5305-0035"));
		assertNotNull("El retiro no existe", retiros);
		log.info(retiros.getRetDescripcion());
	}

	@Test
	public void cTestModificar() throws Exception {
		Retiros retiros  = retirosLogica
				.consultarPorId(new RetirosId(16L, "4008-5305-0035"));
		assertNotNull("El retiro no existe", retiros);
		retiros.setRetDescripcion("Nueva descripcion");
		try {
			retirosLogica.modificar(retiros);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Test
	public void dTestBorrar() throws Exception {
		Retiros retiros  = retirosLogica
				.consultarPorId(new RetirosId(16L, "4008-5305-0035"));
		assertNotNull("El retiro no existe", retiros);
		try {
			retirosLogica.borrar(retiros);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Test
	public void eTestConsultarTodos() throws Exception {
		List<Retiros> losRetiros = retirosLogica.consultarTodos();
		for (Retiros retiros: losRetiros) {
			log.info(retiros.getRetDescripcion());
		}
	}
}
