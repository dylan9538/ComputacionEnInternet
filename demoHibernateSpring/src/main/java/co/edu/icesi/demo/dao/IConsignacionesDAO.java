package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;

public interface IConsignacionesDAO {

	public void grabar(Consignaciones consi);
	public void modificar(Consignaciones consi);
	public void borrar(Consignaciones consi);
	public Consignaciones consultarPorId(ConsignacionesId id);
	public List<Consignaciones> consultarTodos();
}
