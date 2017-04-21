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
import co.edu.icesi.demo.dao.TipoDocumentoDAO;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.TiposDocumentos;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTipoDocumentoDAO {

	private static final Logger log = LoggerFactory.getLogger(TestTipoDocumentoDAO.class);


	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	private long tdocCodigo = 16660L;
	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void aCrearTipoDOcumento() throws Exception {
		TiposDocumentos tipoDocumento = tipoDocumentoDAO.consultarPorId(tdocCodigo);

		assertNull("El documento ya existe", tipoDocumento);

		tipoDocumento = new TiposDocumentos();
		tipoDocumento.setTdocCodigo(tdocCodigo);
		tipoDocumento.setTdocNombre("Nombre2");
		
		
		tipoDocumentoDAO.grabar(tipoDocumento);
	}

	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void bConsultarTipo() throws Exception{
		TiposDocumentos tipoDocumentos = tipoDocumentoDAO.consultarPorId(tdocCodigo);
		assertNotNull("El tipo no existe", tipoDocumentos);
		log.info(tipoDocumentos.getTdocNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void cModificarTipo() throws Exception{
		TiposDocumentos tipoDocumentos = tipoDocumentoDAO.consultarPorId(tdocCodigo);
		assertNotNull("El tipo no existe", tipoDocumentos);
		tipoDocumentos.setTdocNombre("NuevoNombre");
		tipoDocumentoDAO.modificar(tipoDocumentos);
	}
	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void dBorrarCliente() throws Exception{
		TiposDocumentos tipoDocumentos = tipoDocumentoDAO.consultarPorId(tdocCodigo);
		assertNotNull("El cliente no existe", tipoDocumentos);
		tipoDocumentoDAO.borrar(tipoDocumentos);
	}
	
	@Test
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED, rollbackFor =  Exception.class)
	@Rollback(false)
	public void eConsultarTodosTipos() throws Exception{
		List<TiposDocumentos> losTipos = tipoDocumentoDAO.consultarTodos();
        for (TiposDocumentos tiposDoc : losTipos) {
        	log.info("" + tiposDoc.getTdocCodigo());
        	log.info(tiposDoc.getTdocNombre());
		}
		
	}

}
