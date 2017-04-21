package co.edu.icesi.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IConsignacionesDAO;
import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;
import co.edu.icesi.demo.modelo.Cuentas;
import co.edu.icesi.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class ConsignacionesLogica implements IConsignacionesLogica {

	@Autowired
	private IConsignacionesDAO consignacionesDAO;

	@Autowired
	private IUsuariosDAO usuarioDAO;

	@Autowired
	private ICuentasDAO cuentasDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabar(Consignaciones consignaciones) throws Exception {
		if (consignaciones == null) {
			throw new Exception("La consignacion es null");
		}
		if (consignaciones.getId() == null) {
			throw new Exception("El id es obligatorio");
		}
		Consignaciones entity = consignacionesDAO.consultarPorId(consignaciones.getId());
		if (entity != null) {
			throw new Exception("La consignacion con id" + consignaciones.getId().getConCodigo() + " ya existe");
		}
		if (consignaciones.getConDescripcion() == null
				|| consignaciones.getConDescripcion().trim().equals("") == true) {
			throw new Exception("La descripcion no debe ser vacia o nula");
		}
		if (consignaciones.getConFecha() == null) {
			throw new Exception("La fecha es obligatoria");
		}
		if (consignaciones.getConValor() == null) {
			throw new Exception("El valor de la consginación es obligatorio");
		}
		if (consignaciones.getCuentas() == null) {
			throw new Exception("La cuenta es obligatoria");
		}

		Cuentas cuentas = cuentasDAO.consultarPorNumero(consignaciones.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("la cuenta con numero" + consignaciones.getCuentas().getCueNumero() + "ya existe");
		}

		if (consignaciones.getUsuarios() == null) {
			throw new Exception("El usuario es obligatorio");
		}

		Usuarios usuario = usuarioDAO.consultarPorCedula(consignaciones.getUsuarios().getUsuCedula());
		if (usuario == null) {
			throw new Exception("el usuario con cedula" + consignaciones.getUsuarios().getUsuCedula() + "ya existe");
		}
		consignacionesDAO.grabar(consignaciones);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(Consignaciones consignaciones) throws Exception {
		if (consignaciones == null) {
			throw new Exception("La consignacion es null");
		}
		if (consignaciones.getId() == null) {
			throw new Exception("El id es obligatorio");
		}
		if (consignaciones.getConDescripcion() == null
				|| consignaciones.getConDescripcion().trim().equals("") == true) {
			throw new Exception("La descripcion no debe ser vacia o nula");
		}
		if (consignaciones.getConFecha() == null) {
			throw new Exception("La fecha es obligatoria");
		}
		if (consignaciones.getConValor() == null) {
			throw new Exception("El valor de la consginación es obligatorio");
		}
		if (consignaciones.getCuentas() == null) {
			throw new Exception("La cuenta es obligatoria");
		}

		Cuentas cuentas = cuentasDAO.consultarPorNumero(consignaciones.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("la cuenta con numero" + consignaciones.getCuentas().getCueNumero() + "ya existe");
		}

		if (consignaciones.getUsuarios() == null) {
			throw new Exception("El usuario es obligatorio");
		}

		Usuarios usuarios = usuarioDAO.consultarPorCedula(consignaciones.getUsuarios().getUsuCedula());
		if (usuarios == null) {
			throw new Exception("el usuario con cedula" + consignaciones.getUsuarios().getUsuCedula() + "ya existe");
		}
		consignaciones.setCuentas(cuentas);
		consignaciones.setUsuarios(usuarios);
		consignacionesDAO.modificar(consignaciones);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(Consignaciones consignaciones) throws Exception {
		if (consignaciones == null) {
			throw new Exception("La consignacion es null");
		}
		if (consignaciones.getId() == null) {
			throw new Exception("La cedula es obligatorio");
		}
		consignacionesDAO.borrar(consignaciones);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Consignaciones consultarPorId(ConsignacionesId consignacionesId) throws Exception {
		return consignacionesDAO.consultarPorId(consignacionesId);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Consignaciones> consultarTodos() throws Exception {
		return consignacionesDAO.consultarTodos();
	}
}
