package co.edu.icesi.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Cuentas;

@Repository
@Scope("singleton")
public class CuentasDAO implements ICuentasDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void grabar(Cuentas cuentas) {
		sessionFactory.getCurrentSession().save(cuentas);
	}

	@Override
	public void modificar(Cuentas cuentas) {
		sessionFactory.getCurrentSession().update(cuentas);
	}

	@Override
	public void borrar(Cuentas cuentas) {
		sessionFactory.getCurrentSession().delete(cuentas);
	}

	@Override
	public Cuentas consultarPorNumero(String num) {

		return (Cuentas) sessionFactory.getCurrentSession().get(Cuentas.class, num);
	}

	@Override
	public List<Cuentas> consultarTodas() {
		String HQL = "SELECT cu FROM Cuentas cu";
		return sessionFactory.getCurrentSession().createQuery(HQL).list();
	}

}
