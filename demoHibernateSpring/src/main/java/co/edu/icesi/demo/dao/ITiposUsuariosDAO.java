package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.TiposUsuarios;

public interface ITiposUsuariosDAO {
	public void grabar(TiposUsuarios tiposUsuarios);
	public void modificar(TiposUsuarios tiposUsuarios);
	public void borrar(TiposUsuarios tiposUsuarios);
	public TiposUsuarios consultarPorId(long tUsuId);
	public List<TiposUsuarios> consultarTodos();
	
}
