package co.edu.icesi.demo.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

import co.edu.icesi.demo.dao.IConsignacionesDAO;
import co.edu.icesi.demo.dao.ICuentaDAO;
import co.edu.icesi.demo.modelo.ConsignacionePK;
import co.edu.icesi.demo.modelo.Cuenta;
import co.edu.icesi.demo.modelo.Consignacione;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConsignacionesDAO {

	
	private static final Logger log = LoggerFactory.getLogger(TestConsignacionesDAO.class);
	
	@Autowired
	private IConsignacionesDAO ConsignacioneDAO;
	
	@Autowired
	private ICuentaDAO cuentaDAO;
	
	private ConsignacionePK ConsignacioneId = null;
	
	//CrearConsignacion
	@Test
	@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void aTest() throws Exception{
		
		
		ConsignacioneId = new ConsignacionePK();
		ConsignacioneId.setConCodigo(17L);
		ConsignacioneId.setCueNumero("4008-5305-0010");
		
		Consignacione Consignacione = ConsignacioneDAO.consultarPorID(ConsignacioneId);
		
		assertNull("La consignacion ya existe", Consignacione);
		
		Consignacione = new Consignacione();
		Consignacione.setConDescripcion("Consignacion Nueva");
		Consignacione.setConFecha(new Timestamp(1));
		Consignacione.setConValor(new BigDecimal(10000000));
		Consignacione.setId(ConsignacioneId);
		
		Cuenta cuentas = cuentaDAO.consultarPorNumero("4008-5305-0010");
		Consignacione.setCuenta(cuentas);
		
		ConsignacioneDAO.grabar(Consignacione);
		
	}
	
	//Consulta
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void bTest() throws Exception{
				
				ConsignacioneId = new ConsignacionePK();
				ConsignacioneId.setConCodigo(17L);
				ConsignacioneId.setCueNumero("4008-5305-0010");
				Consignacione Consignacione = ConsignacioneDAO.consultarPorID(ConsignacioneId);
				
				assertNotNull("la consignacion no existe", Consignacione);
				
				log.info(Consignacione.getConDescripcion());
				
						
			}
			
			//Modificar
			
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void cTest() throws Exception{
				
				ConsignacioneId = new ConsignacionePK();
				ConsignacioneId.setConCodigo(17L);
				ConsignacioneId.setCueNumero("4008-5305-0010");
				Consignacione Consignacione = ConsignacioneDAO.consultarPorID(ConsignacioneId);
				
				assertNotNull("la consignacion no existe", Consignacione);
				
				Consignacione.setConDescripcion("ABRIR CUENTA");
			
				
				ConsignacioneDAO.modificar(Consignacione);
				
						
			}
			
			//Borrar
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void dTest() throws Exception{
				
				ConsignacioneId = new ConsignacionePK();
				ConsignacioneId.setConCodigo(17L);
				ConsignacioneId.setCueNumero("4008-5305-0010");
				Consignacione Consignacione = ConsignacioneDAO.consultarPorID(ConsignacioneId);
				
				assertNotNull("la consignacion existe",  Consignacione);

				
				ConsignacioneDAO.borrar(Consignacione);
				
						
			}
			
			
			//Consultar Todos
			@Test
			@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
			@Rollback(false)
			public void eTest() throws Exception{
				
				List<Consignacione> lasConsignacione = ConsignacioneDAO.consultarTodos();
				for(Consignacione Consignacione: lasConsignacione){
					log.info("Descripcion: "+Consignacione.getConDescripcion());
					log.info("Valor: "+ Consignacione.getConValor());

				}
				
				
						
			}

}
