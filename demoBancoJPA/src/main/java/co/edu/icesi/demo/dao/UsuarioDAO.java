package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Cliente;
import co.edu.icesi.demo.modelo.Usuario;

@Repository
@Scope("singleton")
public class UsuarioDAO implements IUsuariosDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void grabar(Usuario usuario) {
		entityManager.persist(usuario);

	}

	@Override
	public void modificar(Usuario usuario) {
		entityManager.merge(usuario);
		
	}

	@Override
	public void borrar(Usuario usuario) {
		entityManager.remove(usuario);
		
	}

	@Override
	public Usuario consultarPorCedula(long cedula) {
		return entityManager.find(Usuario.class, cedula);

	}

	@Override
	public List<Usuario> consultarTodos() {
		
		String hql = "SELECT usu FROM Usuario usu";
		return entityManager.createQuery(hql).getResultList();
	}
	
	
	
}
