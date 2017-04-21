package co.edu.icesi.demo.logica;

import java.util.List;

import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;

public interface IConsignacionesLogica {

	public void grabar(Consignaciones consi)throws Exception;
	public void modificar(Consignaciones consi)throws Exception;
	public void borrar(Consignaciones consi)throws Exception;
	public Consignaciones consultarPorId(ConsignacionesId id)throws Exception;
	public List<Consignaciones> consultarTodos()throws Exception;
	
}
