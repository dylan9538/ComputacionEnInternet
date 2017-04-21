package co.edu.icesi.demo.logica;

import java.util.List;

import co.edu.icesi.demo.modelo.Usuarios;;

public interface IUsuarioLogica {

	public void grabar(Usuarios usuarios) throws Exception;

	public void modificar(Usuarios usuarios) throws Exception;

	public void borrar(Usuarios usuarios) throws Exception;

	public Usuarios consultarPorId(long usuCodigo) throws Exception;

	public List<Usuarios> consultarTodos() throws Exception;
	
}
