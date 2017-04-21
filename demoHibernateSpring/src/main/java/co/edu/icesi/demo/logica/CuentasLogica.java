package co.edu.icesi.demo.logica;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IClienteDAO;
import co.edu.icesi.demo.dao.ICuentasDAO;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.Cuentas;

@Service
@Scope("singleton")
public class CuentasLogica implements ICuentasLogica {

	@Autowired
	private ICuentasDAO cuentaDAO;

	@Autowired
	private IClienteDAO clienteDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabar(Cuentas cuentas) throws Exception {
		if (cuentas == null) {
			throw new Exception("La cuenta es null");
		}
		if (cuentas.getCueNumero() == null || cuentas.getCueNumero().trim().equals("") == true) {
			throw new Exception("El numero de cuenta no debe ser vacia o nula");
		}
		Cuentas entity = cuentaDAO.consultarPorNumero(cuentas.getCueNumero());
		if (entity != null) {
			throw new Exception("La cuenta con numero" + cuentas.getCueNumero() + " ya existe");
		}
		if (cuentas.getCueSaldo() == null || cuentas.getCueSaldo() == BigDecimal.ZERO) {
			throw new Exception("El saldo de la cuenta no debe ser cero o null");
		}
		if (cuentas.getCueActiva() == null || cuentas.getCueActiva().trim().equals("") == true) {
			throw new Exception("El estado de cuenta no debe estar vacio o null");
		}
		if (cuentas.getCueClave() == null || cuentas.getCueClave().trim().equals("") == true) {
			throw new Exception("La clave de cuenta es obligatoria");
		}
		if (cuentas.getClientes() == null) {
			throw new Exception("El cliente es obligatorio");
		}

		Clientes clientes = clienteDAO.consultarPorId(cuentas.getClientes().getCliId());
		if (clientes == null) {
			throw new Exception(
					"el cliente con id" + cuentas.getClientes().getCliId() + "ya tiene una cuenta de este tipo");
		}
		cuentaDAO.grabar(cuentas);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(Cuentas cuentas) throws Exception {
		if (cuentas == null) {
			throw new Exception("La cuenta es null");
		}
		if (cuentas.getCueNumero() == null || cuentas.getCueNumero().trim().equals("") == true) {
			throw new Exception("El numero de cuenta no debe ser vacia o nula");
		}
		if (cuentas.getCueSaldo() == null || cuentas.getCueSaldo() == BigDecimal.ZERO) {
			throw new Exception("El saldo de la cuenta no debe ser cero o null");
		}
		if (cuentas.getCueActiva() == null || cuentas.getCueActiva().trim().equals("") == true) {
			throw new Exception("El estado de cuenta no debe estar vacio o null");
		}
		if (cuentas.getCueClave() == null || cuentas.getCueClave().trim().equals("") == true) {
			throw new Exception("La clave de cuenta es obligatoria");
		}
		if (cuentas.getClientes() == null) {
			throw new Exception("El cliente es obligatorio");
		}

		Clientes clientes = clienteDAO.consultarPorId(cuentas.getClientes().getCliId());
		if (clientes == null) {
			throw new Exception(
					"el cliente con id" + cuentas.getClientes().getCliId() + "ya tiene una cuenta de este tipo");
		}
		cuentas.setClientes(clientes);
		cuentaDAO.modificar(cuentas);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(Cuentas cuentas) throws Exception {
		if (cuentas == null) {
			throw new Exception("El usuario es null");
		}
		if (cuentas.getCueNumero().equals("") || cuentas.getCueNumero() == null) {
			throw new Exception("El numero de cuenta es obligatorio");
		}
		cuentaDAO.borrar(cuentas);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Cuentas consultarPorNumero(String numeroCuenta) throws Exception {
		return cuentaDAO.consultarPorNumero(numeroCuenta);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Cuentas> consultarTodos() throws Exception {
		return cuentaDAO.consultarTodas();
	}
}
