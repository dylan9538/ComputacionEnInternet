package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Usuario;

public interface IUsuariosDAO {

	public void grabar(Usuario usuarios);
	public void modificar(Usuario usuarios);
	public void borrar(Usuario usuarios);
	public Usuario consultarPorCedula(long cedula);
	public List<Usuario> consultarTodos();
}
