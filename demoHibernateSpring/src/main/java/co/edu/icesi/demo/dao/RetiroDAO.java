package co.edu.icesi.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Retiros;
import co.edu.icesi.demo.modelo.RetirosId;

@Repository
@Scope("singleton")
public class RetiroDAO implements IRetirosDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(Retiros r) {
		sessionFactory.getCurrentSession().save(r);
		
	}

	@Override
	public void modificar(Retiros r) {
		sessionFactory.getCurrentSession().update(r);
		
	}

	@Override
	public void borrar(Retiros r) {
		sessionFactory.getCurrentSession().delete(r);
		
	}

	@Override
	public Retiros consultarPorId(RetirosId id) {
		return (Retiros) sessionFactory.getCurrentSession().get(Retiros.class, id);
	}

	@Override
	public List<Retiros> consultarTodos() {
		String HQL = "SELECT r FROM Retiros r";
		return sessionFactory.getCurrentSession().createQuery(HQL).list();
	}

}
