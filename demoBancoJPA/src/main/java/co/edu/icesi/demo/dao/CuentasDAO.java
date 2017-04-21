package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Cliente;
import co.edu.icesi.demo.modelo.Cuenta;

@Repository
@Scope("singleton")
public class CuentasDAO implements ICuentaDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void grabar(Cuenta cuenta) {
		entityManager.persist(cuenta);

	}

	@Override
	public void modificar(Cuenta cuenta) {
		entityManager.merge(cuenta);
		
	}

	@Override
	public void borrar(Cuenta cuenta) {
		entityManager.remove(cuenta);
		
	}

	@Override
	public Cuenta consultarPorNumero(String cueNumero) {
		return entityManager.find(Cuenta.class, cueNumero);

	}

	@Override
	public List<Cuenta> consultarTodos() {
		
		String hql = "SELECT cue FROM Cuenta cue";
		return entityManager.createQuery(hql).getResultList();

	}

}
