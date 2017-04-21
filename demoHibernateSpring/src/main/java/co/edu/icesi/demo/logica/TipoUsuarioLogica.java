package co.edu.icesi.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITiposUsuariosDAO;
import co.edu.icesi.demo.modelo.TiposUsuarios;

@Service
@Scope("singleton")
public class TipoUsuarioLogica implements ITipoUsuarioLogica {

	@Autowired
	private ITiposUsuariosDAO tipoUsuarioDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabar(TiposUsuarios tiposUsuarios) throws Exception {
		if (tiposUsuarios == null) {
			throw new Exception("El tipo usuario es null");
		}
		if (tiposUsuarios.getTusuCodigo() == 0) {
			throw new Exception("El codigo del tipo usuario no debe ser cero");
		}
		if (tiposUsuarios.getTusuNombre() == null) {
			throw new Exception("El nombre del tipo usuario no debe ser null");
		}
		if (tiposUsuarios.getTusuNombre().trim().equals("") == true) {
			throw new Exception("El nombre del tipo usuario es obligatorio");
		}
		TiposUsuarios entity = tipoUsuarioDAO.consultarPorId(tiposUsuarios.getTusuCodigo());
		if (entity != null) {
			throw new Exception("El tipo usuario id" + tiposUsuarios.getTusuCodigo() + " ya existe");
		}
		tipoUsuarioDAO.grabar(tiposUsuarios);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(TiposUsuarios tiposUsuarios) throws Exception {
		if (tiposUsuarios == null) {
			throw new Exception("El tipo documento es null");
		}
		if (tiposUsuarios.getTusuCodigo() == 0) {
			throw new Exception("El codigo del tipo usuario no debe ser cero");
		}
		if (tiposUsuarios.getTusuNombre() == null) {
			throw new Exception("El nombre del tipo usuario no debe ser null");
		}
		if (tiposUsuarios.getTusuNombre().trim().equals("") == true) {
			throw new Exception("El nombre del tipo usuario es obligatorio");
		}
		tipoUsuarioDAO.modificar(tiposUsuarios);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(TiposUsuarios tiposUsuarios) throws Exception {
		if (tiposUsuarios== null) {
			throw new Exception("El tipo usuario es null");
		}
		tipoUsuarioDAO.borrar(tiposUsuarios);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public TiposUsuarios consultarPorId(long tusuCodigo) throws Exception {
		return tipoUsuarioDAO.consultarPorId(tusuCodigo);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<TiposUsuarios> consultarTodos() throws Exception {
		return tipoUsuarioDAO.consultarTodos();
	}

}
