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

import co.edu.icesi.demo.dao.ICuentaDAO;
import co.edu.icesi.demo.dao.IRetiroDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.dao.test.TestConsignacionesDAO;

import co.edu.icesi.demo.modelo.Cuenta;
import co.edu.icesi.demo.modelo.Retiro;
import co.edu.icesi.demo.modelo.RetiroPK;

import co.edu.icesi.demo.modelo.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRetiroDAO {

	private static final Logger log = LoggerFactory.getLogger(TestConsignacionesDAO.class);
	
	@Autowired
	private IRetiroDAO retiroDAO;
	@Autowired
	private ICuentaDAO cuentaDAO;
	@Autowired
	private IUsuariosDAO usuarioDAO;
	
	private RetiroPK retirosId = null;
	
	//CrearConsignacion
		@Test
		@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
		@Rollback(false)
		public void aTest() throws Exception{
			
			
			retirosId = new RetiroPK();
			retirosId.setRetCodigo(16L);
			retirosId.setCueNumero("4008-5305-0010");
			
			Retiro retiros = retiroDAO.consultarPorID(retirosId);
			
			assertNull("el retiro ya existe", retiros);
			
			retiros = new Retiro();
			retiros.setId(retirosId);
			retiros.setRetDescripcion("APERTURA DE CUENTA");
			retiros.setRetFecha(new Timestamp(1));
			retiros.setRetValor(new BigDecimal(10000000));
			
			Cuenta cuentas = cuentaDAO.consultarPorNumero("4008-5305-0010");
			retiros.setCuenta(cuentas);
			
			Usuario usuarios = usuarioDAO.consultarPorCedula(10L);
			
			retiros.setUsuario(usuarios);
			
			
			
			retiroDAO.grabar(retiros);
			
		}
		
		
		//Consulta
				@Test
				@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
				@Rollback(false)
				public void bTest() throws Exception{
					
					retirosId = new RetiroPK();
					retirosId.setRetCodigo(16L);
					retirosId.setCueNumero("4008-5305-0010");
					Retiro retiros = retiroDAO.consultarPorID(retirosId);
					
					assertNotNull("el retiro no existe", retiros);
					
					log.info(retiros.getRetDescripcion());
					
							
				}
				
				//Modificar
				
				@Test
				@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
				@Rollback(false)
				public void cTest() throws Exception{
					
					retirosId = new RetiroPK();
					retirosId.setRetCodigo(16L);
					retirosId.setCueNumero("4008-5305-0010");
					Retiro retiros = retiroDAO.consultarPorID(retirosId);
					
					assertNotNull("el retiro no existe", retiros);
					
					retiros.setRetDescripcion("ABRIR CUENTA");
				
					
					retiroDAO.modificar(retiros);
					
							
				}
				
				//Borrar
				@Test
				@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
				@Rollback(false)
				public void dTest() throws Exception{
					
					retirosId = new RetiroPK();
					retirosId.setRetCodigo(16L);
					retirosId.setCueNumero("4008-5305-0010");
					Retiro retiros = retiroDAO.consultarPorID(retirosId);
					
					assertNotNull("el retiro no existe", retiros);

					
					retiroDAO.borrar(retiros);
					
							
				}
				
				
				//Consultar Todos
				@Test
				@Transactional(readOnly= false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
				@Rollback(false)
				public void eTest() throws Exception{
					
					List<Retiro> losRetiros = retiroDAO.consultarTodos();
					for(Retiro retiros: losRetiros){
						log.info("Descripcion: "+retiros.getRetDescripcion());
						log.info("Fecha: "+ retiros.getRetFecha());

					}
					
					
							
				}


			
}
