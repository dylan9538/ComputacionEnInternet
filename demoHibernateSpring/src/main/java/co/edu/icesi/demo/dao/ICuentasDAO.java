package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Cuentas;

public interface ICuentasDAO {
	public void grabar(Cuentas cuentas );
	public void modificar(Cuentas cuentas);
	public void borrar(Cuentas cuentas);
	public Cuentas consultarPorNumero(String num);
	public List<Cuentas> consultarTodas();
}
