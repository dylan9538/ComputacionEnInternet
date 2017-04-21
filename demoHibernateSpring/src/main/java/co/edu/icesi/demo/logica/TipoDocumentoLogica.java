package co.edu.icesi.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITipoDocumentoDAO;
import co.edu.icesi.demo.dao.TipoDocumentoDAO;
import co.edu.icesi.demo.modelo.TiposDocumentos;

//serice es de logica de negocios
@Service
@Scope("singleton")
public class TipoDocumentoLogica implements ITipoDocumentoLogica {

	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabar(TiposDocumentos tiposDocumentos) throws Exception {
		if (tiposDocumentos == null) {
			throw new Exception("El tipo documento es null");
		}
		if (tiposDocumentos.getTdocCodigo() == 0) {
			throw new Exception("El codigo del tipo documento no debe ser cero");
		}
		if (tiposDocumentos.getTdocNombre() == null) {
			throw new Exception("El nombre del tipo documento no debe ser null");
		}
		if (tiposDocumentos.getTdocNombre().trim().equals("") == true) {
			throw new Exception("El nombre del tipo documento es obligatorio");
		}

		TiposDocumentos entity = tipoDocumentoDAO.consultarPorId(tiposDocumentos.getTdocCodigo());
		if (entity != null) {
			throw new Exception("El tipo documento con id" + tiposDocumentos.getTdocCodigo() + " ya existe");
		}
		tipoDocumentoDAO.grabar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(TiposDocumentos tiposDocumentos) throws Exception {
		if (tiposDocumentos == null) {
			throw new Exception("El tipo documento es null");
		}
		if (tiposDocumentos.getTdocCodigo() == 0) {
			throw new Exception("El codigo del tipo documento no debe ser cero");
		}
		if (tiposDocumentos.getTdocNombre() == null) {
			throw new Exception("El nombre del tipo documento no debe ser null");
		}
		if (tiposDocumentos.getTdocNombre().trim().equals("") == true) {
			throw new Exception("El nombre del tipo documento es obligatorio");
		}

		tipoDocumentoDAO.modificar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(TiposDocumentos tiposDocumentos) throws Exception {
		if (tiposDocumentos == null) {
			throw new Exception("El tipo documento es null");
		}

		tipoDocumentoDAO.borrar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public TiposDocumentos consultarPorId(long tdocCodigo) throws Exception {
		return tipoDocumentoDAO.consultarPorId(tdocCodigo);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<TiposDocumentos> consultarTodos() throws Exception {
		return tipoDocumentoDAO.consultarTodos();
	}

}
