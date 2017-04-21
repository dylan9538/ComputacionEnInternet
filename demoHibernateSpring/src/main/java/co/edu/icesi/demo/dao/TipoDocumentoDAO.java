package co.edu.icesi.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.icesi.demo.modelo.TiposDocumentos;

//repository porque se encarga de hacer persistencia
@Repository
@Scope("singleton")
public class TipoDocumentoDAO implements ITipoDocumentoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(TiposDocumentos tiposDocumentos) {
		 sessionFactory.getCurrentSession().save(tiposDocumentos);

	}

	@Override
	public void modificar(TiposDocumentos tiposDocumentos) {
		sessionFactory.getCurrentSession().update(tiposDocumentos);

	}

	@Override
	public void borrar(TiposDocumentos tiposDocumentos) {
		sessionFactory.getCurrentSession().delete(tiposDocumentos);
	}

	@Override
	public TiposDocumentos  consultarPorId(long tdocCodigo) {
		return (TiposDocumentos ) sessionFactory.getCurrentSession().get(TiposDocumentos.class,tdocCodigo);
	}

	@Override
	public List<TiposDocumentos> consultarTodos() {
		String hql = "SELECT doc FROM TiposDocumentos doc";
		return  sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
