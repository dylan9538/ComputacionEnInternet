package co.edu.icesi.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITiposUsuariosDAO;
import co.edu.icesi.demo.dao.IUsuariosDAO;
import co.edu.icesi.demo.modelo.TiposUsuarios;
import co.edu.icesi.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class UsuarioLogica implements IUsuarioLogica {

	@Autowired
	private IUsuariosDAO usuarioDAO;

	@Autowired
	private ITiposUsuariosDAO tipoUsuarioDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabar(Usuarios usuarios) throws Exception {
		if (usuarios == null) {
			throw new Exception("El usuario es null");
		}
		if (usuarios.getUsuCedula() == 0) {
			throw new Exception("La cedula es obligatoria");
		}
		Usuarios entity = usuarioDAO.consultarPorCedula(usuarios.getUsuCedula());
		if (entity != null) {
			throw new Exception("El usuario con id" + usuarios.getUsuCedula() + " ya existe");
		}
		if (usuarios.getUsuClave() == null || usuarios.getUsuClave().trim().equals("") == true) {
			throw new Exception("La clave del usuario no debe ser vacia o nula");
		}
		if (usuarios.getUsuLogin() == null || usuarios.getUsuLogin().trim().equals("") == true) {
			throw new Exception("El login es obligatorio");
		}
		if (usuarios.getUsuNombre() == null || usuarios.getUsuNombre().trim().equals("") == true) {
			throw new Exception("El nombre es obligatorio");
		}
		if (usuarios.getTiposUsuarios() == null) {
			throw new Exception("El tipo de usuario es obligatorio");
		}

		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(usuarios.getTiposUsuarios().getTusuCodigo());
		if (tiposUsuarios == null) {
			throw new Exception(
					"el tipo de usuario con id" + usuarios.getTiposUsuarios().getTusuCodigo() + "ya existe");
		}
		usuarioDAO.grabar(usuarios);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modificar(Usuarios usuarios) throws Exception {
		if (usuarios == null) {
			throw new Exception("El usuario es null");
		}
		if (usuarios.getUsuCedula() == 0) {
			throw new Exception("La cedula es obligatoria");
		}
		if (usuarios.getUsuClave() == null || usuarios.getUsuClave().trim().equals("") == true) {
			throw new Exception("La clave del usuario no debe ser vacia o nula");
		}
		if (usuarios.getUsuLogin() == null || usuarios.getUsuLogin().trim().equals("") == true) {
			throw new Exception("El login es obligatorio");
		}
		if (usuarios.getUsuNombre() == null || usuarios.getUsuNombre().trim().equals("") == true) {
			throw new Exception("El nombre es obligatorio");
		}
		if (usuarios.getTiposUsuarios() == null) {
			throw new Exception("El tipo de usuario es obligatorio");
		}
		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(usuarios.getTiposUsuarios().getTusuCodigo());
		if (tiposUsuarios == null) {
			throw new Exception(
					"el tipo de usuario con id" + usuarios.getTiposUsuarios().getTusuCodigo() + "no existe");
		}
		usuarios.setTiposUsuarios(tiposUsuarios);
		usuarioDAO.modificar(usuarios);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void borrar(Usuarios usuarios) throws Exception {
		if (usuarios == null) {
			throw new Exception("El usuario es null");
		}
		if (usuarios.getUsuCedula() == 0) {
			throw new Exception("La cedula es obligatorio");
		}
		usuarioDAO.borrar(usuarios);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Usuarios consultarPorId(long usuCodigo) throws Exception {
		return usuarioDAO.consultarPorCedula(usuCodigo);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Usuarios> consultarTodos() throws Exception {
		return usuarioDAO.consultarTodos();
	}
}
