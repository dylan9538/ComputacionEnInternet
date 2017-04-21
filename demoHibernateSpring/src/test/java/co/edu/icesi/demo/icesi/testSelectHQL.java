package co.edu.icesi.demo.icesi;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.dto.CuentaSaldos;
import co.edu.icesi.demo.modelo.Clientes;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testSelectHQL {

	private final static Logger log = LoggerFactory.getLogger(testSelectHQL.class);

	Configuration configuration = null;
	StandardServiceRegistryBuilder serviceRegistryBuilder = null;
	SessionFactory sessionFactory = null;

	@Before
	public void antes() {
		configuration = new Configuration();
		configuration.configure();
		serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
	}

	@After
	public void despues() {
		sessionFactory.close();
	}

	@Test
	public void aConsultarCliente() {

		Session session = sessionFactory.openSession();
		String hql = "SELECT c FROM Clientes c";
		Query query = session.createQuery(hql);
		List<Clientes> losClientes = query.list();

		for (Clientes clientes : losClientes) {
			log.info("" + clientes.getCliId());
			log.info(clientes.getCliNombre());
			log.info(clientes.getCliTelefono());
		}
		session.close();
	}

	@Test
	public void aConsultarClientePorNombre() {

		Session session = sessionFactory.openSession();
		String hql = "SELECT c FROM Clientes c WHERE c.cliNombre like '%j%'";
		Query query = session.createQuery(hql);
		List<Clientes> losClientes = query.list();

		for (Clientes clientes : losClientes) {
			log.info("" + clientes.getCliId());
			log.info(clientes.getCliNombre());
			log.info(clientes.getCliTelefono());
		}
		session.close();
	}

	@Test
	public void cConsultarFuncionesAritmeticas() {

		Session session = sessionFactory.openSession();
		String hql = "SELECT MAX(cue.cueSaldo)," + "MIN(cue.cueSaldo)," + "SUM(cue.cueSaldo)," + " AVG(cue.cueSaldo),"
				+ "count(cue) FROM Cuentas cue";
		// corre el query
		Query query = session.createQuery(hql);
		// manda la con a una lista
		Object[] object = (Object[]) query.uniqueResult();
		log.info("max:" + object[0] + "min:" + object[1] + "sum:" + object[2] + "avg:" + object[3] + "count:"
				+ object[4]);

		session.close();
	}

	// CONSULTAS CON PARAMETRO
	@Test
	public void dConsultarFuncionesAritmeticas() {

		Session session = sessionFactory.openSession();
		String hql = "SELECT NEW co.edu.icesi.demo.dto.CuentaSaldos( MAX(cue.cueSaldo)," + "MIN(cue.cueSaldo),"
				+ "SUM(cue.cueSaldo)," + " AVG(cue.cueSaldo)," + "count(cue)) FROM Cuentas cue";
		// corre el query
		Query query = session.createQuery(hql);
		// manda la con a una lista
		CuentaSaldos cuentaSaldos = (CuentaSaldos) query.uniqueResult();
		log.info("\n" + "max:" + cuentaSaldos.getMax());
		log.info("min:" + cuentaSaldos.getMin());
		log.info("sum:" + cuentaSaldos.getSum());
		log.info("avg:" + cuentaSaldos.getAvg());
		log.info("count:" + cuentaSaldos.getCount());

		session.close();
	}

	@Test
	public void eConsultarClientePorNombre() {

		Session session = sessionFactory.openSession();
		String hql = "SELECT c FROM Clientes c WHERE c.cliNombre=:nombre";
		Query query = session.createQuery(hql);
		query.setString("nombre", "Jacob Martin");
		List<Clientes> losClientes = query.list();

		for (Clientes clientes : losClientes) {
			log.info("" + clientes.getCliId());
			log.info(clientes.getCliNombre());
			log.info(clientes.getCliTelefono());
		}
		session.close();
	}
	
	//query tomado del xml

	@Test
	public void fConsultarClientePorNombre() {

		Session session = sessionFactory.openSession();
		String hql = "SELECT c FROM Clientes c WHERE c.cliNombre=:nombre";
		Query query = session.getNamedQuery("consultarClientesPorNombre");
		query.setString("nombre", "Jacob Martin");
		List<Clientes> losClientes = query.list();

		for (Clientes clientes : losClientes) {
			log.info("" + clientes.getCliId());
			log.info(clientes.getCliNombre());
			log.info(clientes.getCliTelefono());
		}
		session.close();
	}

}
