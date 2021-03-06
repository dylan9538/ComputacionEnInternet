package co.edu.icesi.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.icesi.demo.modelo.Clientes;

//repository porque se encarga de hacer persistencia
@Repository
@Scope("singleton")
public class ClienteDAO implements IClienteDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(Clientes clientes) {
		 sessionFactory.getCurrentSession().save(clientes);

	}

	@Override
	public void modificar(Clientes clientes) {
		sessionFactory.getCurrentSession().update(clientes);

	}

	@Override
	public void borrar(Clientes clientes) {
		sessionFactory.getCurrentSession().delete(clientes);
	}

	@Override
	public Clientes consultarPorId(long cliId) {
		return (Clientes) sessionFactory.getCurrentSession().get(Clientes.class,cliId);
	}

	@Override
	public List<Clientes> consultarTodos() {
		String hql = "SELECT cli FROM Clientes cli";
		return  sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
