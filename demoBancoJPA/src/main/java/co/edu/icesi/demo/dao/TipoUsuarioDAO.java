package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.TiposUsuario;

@Repository
@Scope("singleton")
public class TipoUsuarioDAO implements ITipoDeUsuarioDAO {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void grabar(TiposUsuario tipoUsuarios) {
		entityManager.persist(tipoUsuarios);

	}

	@Override
	public void modificar(TiposUsuario tipoUsuarios) {
		entityManager.merge(tipoUsuarios);

	}

	@Override
	public void borrar(TiposUsuario tipoUsuarios) {
		entityManager.remove(tipoUsuarios);

	}

	@Override
	public TiposUsuario consultarPorCodigo(long cod) {
		return entityManager.find(TiposUsuario.class, cod);

	}

	@Override
	public List<TiposUsuario> consultarTodos() {
		String hql = "SELECT tUsu FROM TiposUsuario tUsu";
		return entityManager.createQuery(hql).getResultList();	}

}
