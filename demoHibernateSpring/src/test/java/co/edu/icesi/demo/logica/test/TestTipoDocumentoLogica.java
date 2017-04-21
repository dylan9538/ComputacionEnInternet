package co.edu.icesi.demo.logica.test;

import static org.junit.Assert.*;

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

import co.edu.icesi.demo.logica.ITipoDocumentoLogica;
import co.edu.icesi.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTipoDocumentoLogica {

	private static final Logger log = LoggerFactory.getLogger(TestTipoDocumentoLogica.class);

	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;

	private long id = 60L;

	@Test
	public void aTestCrear() throws Exception {
		TiposDocumentos tiposDocumentos = new TiposDocumentos();
		tiposDocumentos.setTdocCodigo(id);
		tiposDocumentos.setTdocNombre("TARJETA DE PROPIEDAD");
		try {
			tipoDocumentoLogica.grabar(tiposDocumentos);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Test
	public void bTestConsultar() throws Exception {
		TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(id);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);
		log.info(tiposDocumentos.getTdocNombre());
	}
	
	@Test
	public void cTestModificar() throws Exception {
		TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(id);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);
		tiposDocumentos.setTdocNombre("nuevo nombre");
		
		try {
			tipoDocumentoLogica.modificar(tiposDocumentos);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void dTestBorrar() throws Exception {
		TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(id);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);
		try {
			tipoDocumentoLogica.borrar(tiposDocumentos);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
	}
	
	@Test
	public void eTestConsultarTodos() throws Exception {
		List<TiposDocumentos> losTiposDocumentos = tipoDocumentoLogica.consultarTodos();
		for (TiposDocumentos tiposDocumentos : losTiposDocumentos) {
			log.info(tiposDocumentos.getTdocNombre());
		}
		
	}

}
