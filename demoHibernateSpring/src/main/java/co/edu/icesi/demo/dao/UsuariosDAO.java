package co.edu.icesi.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Usuarios;

@Repository
@Scope("singleton")
public class UsuariosDAO implements IUsuariosDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(Usuarios usu) {
		sessionFactory.getCurrentSession().save(usu);
	}

	@Override
	public void modificar(Usuarios usu) {
		sessionFactory.getCurrentSession().update(usu);
		
	}

	@Override
	public void borrar(Usuarios usu) {
		sessionFactory.getCurrentSession().delete(usu);
		
	}

	@Override
	public Usuarios consultarPorCedula(long cedula) {
		
		return (Usuarios) sessionFactory.getCurrentSession().get(Usuarios.class,cedula);
	}

	@Override
	public List<Usuarios> consultarTodos() {
		String HQL ="SELECT u FROM Usuarios u";
		return sessionFactory.getCurrentSession().createQuery(HQL).list();
	}

	
}
