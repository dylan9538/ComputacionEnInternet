package co.edu.icesi.demo.dao.test;

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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IRetirosDAO;
import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.dao.IRetirosDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRetiroDAO {

	private static final Logger log = LoggerFactory.getLogger(TestRetiroDAO.class);

	@Autowired
	private IRetirosDAO retirosDAO;

	@Autowired
	private ICuentasDAO cuentasDAO;

	@Autowired
	private IUsuariosDAO usuariosDAO;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void aCrearRetiro() throws Exception {
		Retiros r = retirosDAO.consultarPorId(new RetirosId(16L, "4008-5305-0020"));

		assertNull("El retiro ya se hizo", r);

		r = new Retiros();
		r.setRetDescripcion("RETIRO1");
		r.setRetFecha(new Date());
		r.setRetValor(new BigDecimal("300000.00"));
		Cuentas cuentas = (Cuentas) cuentasDAO.consultarPorNumero("4008-5305-0020");
		r.setCuentas(cuentas);
		r.setId(new RetirosId(16L, cuentas.getCueNumero()));
		Usuarios u = (Usuarios) usuariosDAO.consultarPorCedula(20L);
		r.setUsuarios(u);

		retirosDAO.grabar(r);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void bConsultarRetiro() throws Exception {
		Retiros r = retirosDAO.consultarPorId(new RetirosId(16L, "4008-5305-0020"));
		assertNotNull("La consignacion no esta en el sistema", r);
		log.info(r.getRetDescripcion());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void cModificarRetiro() throws Exception {
		Retiros r = retirosDAO.consultarPorId(new RetirosId(16L, "4008-5305-0020"));

		assertNotNull("El retiro no esta en el sistema", r);
		r.setRetDescripcion("CAMBIO DE DESCRIPCION");
		r.setRetValor(new BigDecimal("400000.00"));

		retirosDAO.modificar(r);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void dBorrarRetiro() throws Exception {
		Retiros r = retirosDAO.consultarPorId(new RetirosId(16L, "4008-5305-0020"));

		assertNotNull("El retiro no esta en el sistema", r);
		retirosDAO.borrar(r);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void eConsultarTodasRetiros() throws Exception {
		List<Retiros> losRetiros = retirosDAO.consultarTodos();
		for (Retiros retiros : losRetiros) {
			log.info(retiros.getId() + "");
			log.info(retiros.getRetDescripcion());
		}

	}

}
