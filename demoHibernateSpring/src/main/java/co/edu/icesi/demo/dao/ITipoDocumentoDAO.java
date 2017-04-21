package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.TiposDocumentos;

public interface ITipoDocumentoDAO {

	public void grabar(TiposDocumentos tiposDocumentos);
	public void modificar(TiposDocumentos tiposDocumentos);
	public void borrar(TiposDocumentos tiposDocumentos);
	public TiposDocumentos  consultarPorId(long tdocCodigo);
	public List<TiposDocumentos > consultarTodos();
	
}
