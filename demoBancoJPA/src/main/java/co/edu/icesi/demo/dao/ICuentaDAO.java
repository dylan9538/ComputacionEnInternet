package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Cuenta;

public interface ICuentaDAO {

	
	public void grabar(Cuenta cuentas);
	public void modificar(Cuenta cuentas);
	public void borrar(Cuenta cuentas);
	public Cuenta consultarPorNumero(String cueNumero);
	public List<Cuenta> consultarTodos();
}
