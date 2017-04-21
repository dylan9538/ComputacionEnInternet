package co.edu.icesi.demo.icesi;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.icesi.demo.modelo.Clientes;
import co.edu.icesi.demo.modelo.TiposUsuarios;
import co.edu.icesi.demo.modelo.Usuarios;

public class MetodosALaBaseDatos {
	
	private final static Logger log = LoggerFactory.getLogger(MetodosALaBaseDatos.class);
	

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
	// consultar
	public void atest() {

		Session session = sessionFactory.openSession();
		Usuarios usuarios = (Usuarios) session.get(Usuarios.class, 15L);
		assertNotNull("El usuario no existe", usuarios);
		
		log.info("" + usuarios.getUsuCedula());
		log.info("" + usuarios.getUsuClave());
		log.info("" + usuarios.getUsuNombre());
		log.info("" + usuarios.getUsuLogin());
		
		log.info("" + usuarios.getTiposUsuarios().getTusuCodigo());
		
		session.close();

	}

	@Test
	// crear
	public void btest() {
		Session session = sessionFactory.openSession();
		Usuarios usu = new Usuarios();
		usu.setUsuNombre("CARLOS PEREZ");
		usu.setUsuCedula(10L);
		usu.setUsuClave("1234");
		usu.setUsuLogin("234");

		TiposUsuarios tiposUsuarios = (TiposUsuarios) session.get(TiposUsuarios.class, 10L);
		usu.setTiposUsuarios(tiposUsuarios);

		session.getTransaction().begin();
		session.save(usu);
		session.getTransaction().commit();
		session.close();

	}

	@Test
	// modificar
	public void ctest() {
		Session session = sessionFactory.openSession();
		Usuarios usu = (Usuarios) session.get(Usuarios.class, 10L);

		usu.setUsuNombre("JOSE PEREZ");

		session.getTransaction().begin();
		session.update(usu);
		session.getTransaction().commit();
		session.close();
	}

	@Test
	// modificar
	public void dtest() {
		Session session = sessionFactory.openSession();
		Usuarios usu = (Usuarios) session.get(Usuarios.class, 10L);

		session.getTransaction().begin();
		session.delete(usu);
		session.getTransaction().commit();
		session.close();

	}

}
