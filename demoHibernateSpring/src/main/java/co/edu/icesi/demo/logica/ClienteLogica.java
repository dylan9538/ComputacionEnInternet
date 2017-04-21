package co.edu.icesi.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.IClienteDAO;
import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class ClienteLogica implements IClienteLogica {

	@Autowired
	private IClienteDAO clienteDAO;

	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabar(Clientes clientes) throws Exception {
		if (clientes == null) {
			throw new Exception("El cliente es null");
		}

		if (clientes.getCliId() == 0) {
			throw new Exception("El id es obligatorio");
		}
		Clientes entity = clienteDAO.consultarPorId(clientes.getCliId());
		if (entity != null) {
			throw new Exception("El cliente con id" + clientes.getCliId() + " ya existe");
		}
		if (clientes.getCliDireccion() == null || clientes.getCliDireccion().trim().equals("") == true) {
			throw new Exception("La direccion del cliente no debe ser vacia o nula");
		}
		
		if (clientes.getCliMail() == null || clientes.getCliMail().trim().equals("") == true) {
			throw new Exception("El mail es obligatorio");
		}
		if (clientes.getCliNombre() == null || clientes.getCliNombre().trim().equals("") == true) {
			throw new Exception("El nombre es obligatorio");
		}
		if (clientes.getCliTelefono() == null || clientes.getCliTelefono().trim().equals("") == true) {
			throw new Exception("El telefono es obligatorio");
		}
		if (clientes.getTiposDocumentos() == null) {
			throw new Exception("El tipo de documento es obligatorio");
		}

		TiposDocumentos tiposDocumentos = tipoDocumentoLogica
				.consultarPorId(clientes.getTiposDocumentos().getTdocCodigo());
		if (tiposDocumentos == null) {
			throw new Exception(
					"el tipo de documento con id" + clientes.getTiposDocumentos().getTdocCodigo() + "ya existe");
		}
		clienteDAO.grabar(clientes);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(Clientes clientes) throws Exception {
		if (clientes == null) {
			throw new Exception("El cliente es null");
		}

		if (clientes.getCliId() == 0) {
			throw new Exception("El id es obligatorio");
		}

		if (clientes.getCliDireccion() == null || clientes.getCliDireccion().trim().equals("") == true) {
			throw new Exception("La direccion del cliente no debe ser vacia o nula");
		}
		if (clientes.getCliMail() == null || clientes.getCliMail().trim().equals("") == true) {
			throw new Exception("El mail es obligatorio");
		}
		if (clientes.getCliNombre() == null || clientes.getCliNombre().trim().equals("") == true) {
			throw new Exception("El nombre es obligatorio");
		}
		if (clientes.getCliTelefono() == null || clientes.getCliTelefono().trim().equals("") == true) {
			throw new Exception("El telefono es obligatorio");
		}
		if (clientes.getTiposDocumentos() == null) {
			throw new Exception("El tipo de documento es obligatorio");
		}

		TiposDocumentos tiposDocumentos = tipoDocumentoLogica
				.consultarPorId(clientes.getTiposDocumentos().getTdocCodigo());
		if (tiposDocumentos == null) {
			throw new Exception(
					"el tipo de documento con id" + clientes.getTiposDocumentos().getTdocCodigo() + "no existe");
		}
		clientes.setTiposDocumentos(tiposDocumentos);
		clienteDAO.modificar(clientes);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(Clientes clientes) throws Exception {
		if (clientes == null) {
			throw new Exception("El cliente es null");
		}
		if (clientes.getCliId() == 0) {
			throw new Exception("El id es obligatorio");
		}
		clienteDAO.borrar(clientes);
	}

	@Override
	@Transactional(readOnly = true)
	public Clientes consultarPorId(long cliId) throws Exception {
		return clienteDAO.consultarPorId(cliId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Clientes> consultarTodos() throws Exception {
		return clienteDAO.consultarTodos();
	}

}
