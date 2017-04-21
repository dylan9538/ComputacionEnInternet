package co.edu.icesi.demo.logica;

import java.util.List;
import co.edu.icesi.demo.modelo.TiposDocumentos;

public interface ITipoDocumentoLogica {

	public void grabar(TiposDocumentos tiposDocumentos) throws Exception;

	public void modificar(TiposDocumentos tiposDocumentos) throws Exception;

	public void borrar(TiposDocumentos tiposDocumentos) throws Exception;

	public TiposDocumentos consultarPorId(long tdocCodigo) throws Exception;

	public List<TiposDocumentos> consultarTodos() throws Exception;

}
