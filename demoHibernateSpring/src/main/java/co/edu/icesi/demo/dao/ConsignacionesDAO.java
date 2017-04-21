package co.edu.icesi.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Consignaciones;
import co.edu.icesi.demo.modelo.ConsignacionesId;

@Repository
@Scope("singleton")
public class ConsignacionesDAO implements IConsignacionesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void grabar(Consignaciones consi) {
		sessionFactory.getCurrentSession().save(consi);
	}

	@Override
	public void modificar(Consignaciones consi) {
		sessionFactory.getCurrentSession().update(consi);
	}

	@Override
	public void borrar(Consignaciones consi) {
		sessionFactory.getCurrentSession().delete(consi);
	}

	@Override
	public Consignaciones consultarPorId(ConsignacionesId c) {

		return (Consignaciones) sessionFactory.getCurrentSession().get(Consignaciones.class, c);
	}

	@Override
	public List<Consignaciones> consultarTodos() {
		String HQL = "SELECT c FROM Consignaciones c";
		return sessionFactory.getCurrentSession().createQuery(HQL).list();
	}

}
