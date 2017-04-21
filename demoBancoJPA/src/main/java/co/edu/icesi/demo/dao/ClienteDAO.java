package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.modelo.Cliente;

@Repository
@Scope("singleton")
public class ClienteDAO implements IClienteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void grabar(Cliente clientes) {

		entityManager.persist(clientes);
	}

	@Override
	public void modificar(Cliente clientes) {
		entityManager.merge(clientes);
	}

	@Override
	public void borrar(Cliente clientes) {
		entityManager.remove(clientes);
	}

	@Override
	public Cliente consultarPorID(long cliId) {
		return entityManager.find(Cliente.class, cliId);
	}

	@Override
	public List<Cliente> consultarTodos() {

		String hql = "SELECT cli FROM Cliente cli";
		return entityManager.createQuery(hql).getResultList();
	}

}
