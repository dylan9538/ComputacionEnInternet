package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Cliente;
import co.edu.icesi.demo.modelo.Retiro;
import co.edu.icesi.demo.modelo.RetiroPK;

@Repository
@Scope("singleton")
public class RetiroDAO implements IRetiroDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void grabar(Retiro retiros) {
		entityManager.persist(retiros);
		
	}

	public void modificar(Retiro retiros) {
		entityManager.merge(retiros);
		
	}

	public void borrar(Retiro retiros) {
		entityManager.remove(retiros);
		
	}

	public Retiro consultarPorID(RetiroPK retId) {
		return entityManager.find(Retiro.class, retId);

	}

	public List<Retiro> consultarTodos() {
		String hql = "SELECT ret FROM Retiro ret";
		return entityManager.createQuery(hql).getResultList();
	}

}
