package co.edu.icesi.demo.logica;

import java.util.List;

import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;

public interface IRestirosLogica {

	public void grabar(Retiros r) throws Exception;
	public void modificar(Retiros r)throws Exception;
	public void borrar(Retiros r)throws Exception;
	public Retiros consultarPorId(RetirosId id)throws Exception;
	public List<Retiros> consultarTodos()throws Exception;
	
}
