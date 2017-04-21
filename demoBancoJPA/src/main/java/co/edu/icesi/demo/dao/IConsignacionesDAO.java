package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Consignacione;
import co.edu.icesi.demo.modelo.ConsignacionePK;

public interface IConsignacionesDAO {

	
	public void grabar(Consignacione consignaciones);
	public void modificar(Consignacione consignaciones);
	public void borrar(Consignacione consignaciones);
	public Consignacione consultarPorID(ConsignacionePK conId);
	public List<Consignacione> consultarTodos();
}
