package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Cliente;

public interface IClienteDAO {

	public void grabar(Cliente clientes)throws Exception;
	public void modificar(Cliente clientes)throws Exception;
	public void borrar(Cliente clientes)throws Exception;
	public Cliente consultarPorID(long cliId)throws Exception;
	public List<Cliente> consultarTodos()throws Exception;

	
	
	
	
}
