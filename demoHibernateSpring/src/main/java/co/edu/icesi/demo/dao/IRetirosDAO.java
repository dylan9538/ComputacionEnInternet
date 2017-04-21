package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;;

public interface IRetirosDAO {

	public void grabar(Retiros r);
	public void modificar(Retiros r);
	public void borrar(Retiros r);
	public Retiros consultarPorId(RetirosId id);
	public List<Retiros> consultarTodos();
	
}
