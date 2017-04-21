package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Usuarios;

public interface IUsuariosDAO {

	public void grabar(Usuarios usu);
	public void modificar(Usuarios usu);
	public void borrar(Usuarios usu);
	public Usuarios consultarPorCedula(long cedula);
	public List<Usuarios> consultarTodos();
	
	
}
