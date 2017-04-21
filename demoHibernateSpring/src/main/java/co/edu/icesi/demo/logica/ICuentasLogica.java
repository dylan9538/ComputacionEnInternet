package co.edu.icesi.demo.logica;

import java.util.List;

import co.edu.icesi.demo.modelo.Cuentas;

public interface ICuentasLogica {

	public void grabar(Cuentas cuentas) throws Exception;
	public void modificar(Cuentas cuentas) throws Exception;
	public void borrar(Cuentas cuentas) throws Exception;
	public Cuentas consultarPorNumero( String numeroCuenta) throws Exception;
	public List<Cuentas> consultarTodos() throws Exception;
}
