package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.modelo.TiposDocumento;

public interface ITipoDocumentoDAO {
	
	public void grabar(TiposDocumento tiposDocumentos);
	public void modificar(TiposDocumento tiposDocumentos);
	public void borrar(TiposDocumento tiposDocumentos);
	public TiposDocumento consultarPorID(long tdocCodigo);
	public List<TiposDocumento> consultarTodos();

}
