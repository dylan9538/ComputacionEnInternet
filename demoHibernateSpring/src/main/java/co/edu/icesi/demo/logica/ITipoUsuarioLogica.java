package co.edu.icesi.demo.logica;

import java.util.List;

import co.edu.icesi.demo.modelo.TiposUsuarios;;

public interface ITipoUsuarioLogica {

	public void grabar(TiposUsuarios tiposUsuarios) throws Exception;

	public void modificar(TiposUsuarios tiposUsuarios) throws Exception;

	public void borrar(TiposUsuarios tiposUsuarios) throws Exception;

	public TiposUsuarios consultarPorId(long tusuCodigo) throws Exception;

	public List<TiposUsuarios> consultarTodos() throws Exception;

}
