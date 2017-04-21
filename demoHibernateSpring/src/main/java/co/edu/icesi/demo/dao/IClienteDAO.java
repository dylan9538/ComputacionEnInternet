package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Clientes;

public interface IClienteDAO {

	public void grabar(Clientes clientes);
	public void modificar(Clientes clientes);
	public void borrar(Clientes clientes);
	public Clientes consultarPorId(long cliId);
	public List<Clientes> consultarTodos();
	
}
