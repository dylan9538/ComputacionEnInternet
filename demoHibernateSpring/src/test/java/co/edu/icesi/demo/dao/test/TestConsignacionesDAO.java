package co.edu.icesi.demo.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
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

import co.edu.icesi.demo.dao.IConsignacionesDAO;
import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.TiposDocumentos;
import co.edu.icesi.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConsignacionesDAO {

	private static final Logger log = LoggerFactory.getLogger(TestConsignacionesDAO.class);

	@Autowired
	private IConsignacionesDAO consignacionesDAO;

	@Autowired
	private ICuentasDAO cuentasDAO;

	@Autowired
	private IUsuariosDAO usuariosDAO;


	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void aCrearConsignacion() throws Exception {
		Consignaciones con = consignacionesDAO.consultarPorId(new ConsignacionesId(16L, "4008-5305-0020"));

		//assertNull("El codigo de consignacion ya existe", con);

		con = new Consignaciones();
		con.setConDescripcion("HOLA MUNDO");
		con.setConFecha(new Date());
		con.setConValor(new BigDecimal("300000.00"));
		Cuentas cuentas = (Cuentas) cuentasDAO.consultarPorNumero("4008-5305-0020");
		con.setCuentas(cuentas);
		con.setId(new ConsignacionesId(16L, cuentas.getCueNumero()));
		Usuarios u = (Usuarios) usuariosDAO.consultarPorCedula(20L);
		con.setUsuarios(u);

		consignacionesDAO.grabar(con);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void bConsultarConssignacion() throws Exception {
		Consignaciones con = consignacionesDAO.consultarPorId(new ConsignacionesId(16L, "4008-5305-0020"));
		assertNotNull("La consignacion no esta en el sistema", con);
		log.info(con.getConDescripcion());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void cModificarConsignacion() throws Exception {
		Consignaciones con =  consignacionesDAO.consultarPorId(new ConsignacionesId(16L, "4008-5305-0020"));

		assertNotNull("La consignacion no esta en el sistema", con);
		con.setConDescripcion("CAMBIO DE DESCRIPCION");
		con.setConValor(new BigDecimal("400000.00"));

		consignacionesDAO.modificar(con);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void dBorrarConsignacion() throws Exception {
		Consignaciones con = consignacionesDAO.consultarPorId(new ConsignacionesId(16L, "4008-5305-0020"));

		assertNotNull("La consignacion no esta en el sistema", con);
		consignacionesDAO.borrar(con);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void eConsultarTodasConsignaciones() throws Exception {
		List<Consignaciones> losConsignaciones = consignacionesDAO.consultarTodos();
		for (Consignaciones consignaciones : losConsignaciones) {
			log.info(consignaciones.getId() + "");
			log.info(consignaciones.getConDescripcion());
		}

	}

}
