package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Cliente;
import co.edu.icesi.demo.modelo.TiposDocumento;

@Repository
@Scope("singleton")
public class TipoDocumentoDAO implements ITipoDocumentoDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void grabar(TiposDocumento tiposDocumentos) {
		entityManager.persist(tiposDocumentos);
	}

	@Override
	public void modificar(TiposDocumento tiposDocumentos) {
		entityManager.merge(tiposDocumentos);
	}

	@Override
	public void borrar(TiposDocumento tiposDocumentos) {
		entityManager.remove(tiposDocumentos);

	}

	@Override
	public TiposDocumento consultarPorID(long tdocCodigo) {
		return entityManager.find(TiposDocumento.class, tdocCodigo);

	}

	@Override
	public List<TiposDocumento> consultarTodos() {
		String hql = "SELECT tdoc FROM TiposDocumento tdoc";
		return entityManager.createQuery(hql).getResultList();	}

}
