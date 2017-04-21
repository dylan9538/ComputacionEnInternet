package co.edu.icesi.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.TiposDocumentos;
import co.edu.icesi.demo.modelo.TiposUsuarios;

@Repository
@Scope("singleton")
public class TipoUsuarioDAO implements ITiposUsuariosDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(TiposUsuarios tiposUsuarios) {
		sessionFactory.getCurrentSession().save(tiposUsuarios);
		
	}

	@Override
	public void modificar(TiposUsuarios tiposUsuarios) {
		sessionFactory.getCurrentSession().update(tiposUsuarios);
		
	}

	@Override
	public void borrar(TiposUsuarios tiposUsuarios) {
		sessionFactory.getCurrentSession().delete(tiposUsuarios);
		
	}

	@Override
	public TiposUsuarios  consultarPorId(long tusuCodigo) {
		return (TiposUsuarios ) sessionFactory.getCurrentSession().get(TiposUsuarios.class,tusuCodigo);
	}

	@Override
	public List<TiposUsuarios> consultarTodos() {
		String hql = "SELECT tUsu FROM TiposUsuarios tUsu";
		return  sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}
