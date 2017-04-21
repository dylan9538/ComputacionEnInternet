package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.TiposUsuario;

public interface ITipoDeUsuarioDAO {
	
	public void grabar(TiposUsuario tipoUsuarios);
	public void modificar(TiposUsuario tipoUsuarios);
	public void borrar(TiposUsuario tipoUsuarios);
	public TiposUsuario consultarPorCodigo(long cod);
	public List<TiposUsuario> consultarTodos();
}
