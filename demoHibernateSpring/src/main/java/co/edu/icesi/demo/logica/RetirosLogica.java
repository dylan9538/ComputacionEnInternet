package co.edu.icesi.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.dao.IRetirosDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;
import co.edu.icesi.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class RetirosLogica implements IRestirosLogica {

	@Autowired
	private IRetirosDAO retirosDAO;

	@Autowired
	private IUsuariosDAO usuarioDAO;

	@Autowired
	private ICuentasDAO cuentasDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabar(Retiros retiros) throws Exception {
		if (retiros == null) {
			throw new Exception("La consignacion es null");
		}
		if (retiros.getId() == null) {
			throw new Exception("El id es obligatorio");
		}
		Retiros entity = retirosDAO.consultarPorId(retiros.getId());
		if (entity != null) {
			throw new Exception("La consignacion con id" + retiros.getId().getRetCodigo() + " ya existe");
		}
		if (retiros.getRetDescripcion() == null
				|| retiros.getRetDescripcion().trim().equals("") == true) {
			throw new Exception("La descripcion no debe ser vacia o nula");
		}
		if (retiros.getRetFecha() == null) {
			throw new Exception("La fecha es obligatoria");
		}
		if (retiros.getRetValor() == null) {
			throw new Exception("El valor de la consginación es obligatorio");
		}
		if (retiros.getCuentas() == null) {
			throw new Exception("La cuenta es obligatoria");
		}

		Cuentas cuentas = cuentasDAO.consultarPorNumero(retiros.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("la cuenta con numero" + retiros.getCuentas().getCueNumero() + "ya existe");
		}

		if (retiros.getUsuarios() == null) {
			throw new Exception("El usuario es obligatorio");
		}

		Usuarios usuario = usuarioDAO.consultarPorCedula(retiros.getUsuarios().getUsuCedula());
		if (usuario == null) {
			throw new Exception("el usuario con cedula" + retiros.getUsuarios().getUsuCedula() + "ya existe");
		}
		retirosDAO.grabar(retiros);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(Retiros retiros) throws Exception {
		if (retiros == null) {
			throw new Exception("La consignacion es null");
		}
		if (retiros.getId() == null) {
			throw new Exception("El id es obligatorio");
		}
		if (retiros.getRetDescripcion() == null
				|| retiros.getRetDescripcion().trim().equals("") == true) {
			throw new Exception("La descripcion no debe ser vacia o nula");
		}
		if (retiros.getRetFecha() == null) {
			throw new Exception("La fecha es obligatoria");
		}
		if (retiros.getRetValor() == null) {
			throw new Exception("El valor de la consginación es obligatorio");
		}
		if (retiros.getCuentas() == null) {
			throw new Exception("La cuenta es obligatoria");
		}

		Cuentas cuentas = cuentasDAO.consultarPorNumero(retiros.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("la cuenta con numero" + retiros.getCuentas().getCueNumero() + "ya existe");
		}

		if (retiros.getUsuarios() == null) {
			throw new Exception("El usuario es obligatorio");
		}

		Usuarios usuarios = usuarioDAO.consultarPorCedula(retiros.getUsuarios().getUsuCedula());
		if (usuarios == null) {
			throw new Exception("el usuario con cedula" + retiros.getUsuarios().getUsuCedula() + "ya existe");
		}
		retiros.setCuentas(cuentas);
		retiros.setUsuarios(usuarios);
		retirosDAO.modificar(retiros);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(Retiros retiros) throws Exception {
		if (retiros == null) {
			throw new Exception("el retiro es null");
		}
		if (retiros.getId() == null) {
			throw new Exception("el Id es obligatorio");
		}
		retirosDAO.borrar(retiros);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Retiros consultarPorId(RetirosId retirosId) throws Exception {
		return retirosDAO.consultarPorId(retirosId);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Retiros> consultarTodos() throws Exception {
		return retirosDAO.consultarTodos();
	}
}
