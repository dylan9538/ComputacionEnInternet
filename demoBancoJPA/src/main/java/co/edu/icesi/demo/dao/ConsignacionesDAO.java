package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Consignacione;
import co.edu.icesi.demo.modelo.ConsignacionePK;

@Repository
@Scope("singleton")
public class ConsignacionesDAO implements IConsignacionesDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void grabar(Consignacione consignaciones) {
		entityManager.persist(consignaciones);
		
	}

	@Override
	public void modificar(Consignacione consignaciones) {
		entityManager.merge(consignaciones);
		
	}

	@Override
	public void borrar(Consignacione consignaciones) {
		entityManager.remove(consignaciones);
		
	}

	@Override
	public Consignacione consultarPorID(ConsignacionePK conId) {
		return entityManager.find(Consignacione.class, conId);

	}

	@Override
	public List<Consignacione> consultarTodos() {
		String hql = "SELECT consi FROM Consignacione consi";
		return entityManager.createQuery(hql).getResultList();

	}
	

}
