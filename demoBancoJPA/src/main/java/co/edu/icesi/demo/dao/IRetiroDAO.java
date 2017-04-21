package co.edu.icesi.demo.dao;

import java.util.List;


import co.edu.icesi.demo.modelo.Retiro;
import co.edu.icesi.demo.modelo.RetiroPK;

public interface IRetiroDAO {

	public void grabar(Retiro retiros);
	public void modificar(Retiro retiros);
	public void borrar(Retiro retiros);
	public Retiro consultarPorID(RetiroPK retId);
	public List<Retiro> consultarTodos();
}
