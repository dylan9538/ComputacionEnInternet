package co.edu.icesi.tienda.modelo;

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


import co.edu.icesi.tienda.mascotas.modelo.Clientes;
import co.edu.icesi.tienda.mascotas.modelo.Mascotas;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMascotasHQL {

	private final static Logger log = LoggerFactory.getLogger(TestMascotasHQL.class);

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
	public void aCrearCliente() {
		Session session = sessionFactory.openSession();
		Clientes cliente = new Clientes();
		cliente.setCliCedula(134L);
		cliente.setCliDireccion("Gaitan");
		cliente.setCliNombre("DYLAN");
		cliente.setCliTelefono("4330199");	

		session.getTransaction().begin();
		session.save(cliente);
		session.getTransaction().commit();
		session.close();

			}
	
	@Test
	public void bModificarCliente(){
		Session session = sessionFactory.openSession();
		Clientes cliente = (Clientes) session.get(Clientes.class, 134L);
		cliente.setCliNombre("CAMILO");	
		session.getTransaction().begin();
		session.update(cliente);
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void cBorrarCliente(){
		Session session = sessionFactory.openSession();
		Clientes cliente = (Clientes) session.get(Clientes.class, 134L);
		
		session.getTransaction().begin();
		session.delete(cliente);
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void dCrearMascota() {
		Session session = sessionFactory.openSession();
		Mascotas mascotas = new Mascotas();
		mascotas.setMasCodigo(60L);
		mascotas.setMasEdad(18L);
		mascotas.setMasNombre("Prince");
		
		Clientes cliente =(Clientes) session.get(Clientes.class, 10L);
        mascotas.setClientes(cliente);

		session.getTransaction().begin();
		session.save(mascotas);
		session.getTransaction().commit();
		session.close();

			}
	
	@Test
	public void eModificarMascota(){
		Session session = sessionFactory.openSession();
		Mascotas mascota = (Mascotas) session.get(Mascotas.class, 60L);
		mascota.setMasNombre("PACO");	
		
		session.getTransaction().begin();
		session.update(mascota);
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void fBorrarMascota(){
		Session session = sessionFactory.openSession();
		Mascotas mascota = (Mascotas) session.get(Mascotas.class, 60L);
	
		session.getTransaction().begin();
		session.delete(mascota);
		session.getTransaction().commit();
		session.close();	
	}
	
	@Test
	public void gConsultarMascotaDeCliente(){
			Session session = sessionFactory.openSession();
			String hql = "SELECT c.mascotases FROM Clientes c WHERE c.cliCedula=:cedula";
			Query query = session.createQuery(hql);
			query.setLong("cedula", 30L);
			List<Mascotas> lasMascotas = query.list();

			for (Mascotas m : lasMascotas) {
				log.info("" + m.getMasNombre());	
			}
			session.close();
		
	}
	
	@Test
	public void hConsultarMascotasPorNombre(){
		Session session = sessionFactory.openSession();
		String hql = "SELECT m FROM Mascotas m WHERE m.masNombre=:nombre";
		Query query = session.createQuery(hql);
		query.setString("nombre", "PELUSA");
		List<Mascotas> lasMascotas = query.list();

		for (Mascotas mascotas : lasMascotas) {
			log.info("" + mascotas.getMasCodigo());
			log.info("" +mascotas.getMasEdad());
			log.info(mascotas.getMasNombre());
		}
		session.close();
	}
	
	@Test 
	public void iConsultarClienteConMascotaParticular(){
		Session session = sessionFactory.openSession();
		String hql = "SELECT DISTINCT vacu.mascotas.clientes FROM VacunasMascotas "
				+ "vacu WHERE vacu.vacunas.vacNombre=:nombreVacuna";
		Query query = session.createQuery(hql);
		query.setString("nombreVacuna", "POLIO");
		List<Clientes> losClientes = query.list();

		for (Clientes clientes : losClientes) {
			log.info("" + clientes.getCliNombre());	
		}
		session.close();
	}
	
	
	@Test 
	public void jConsultarMascotaConMasDeTresAños(){
		Session session = sessionFactory.openSession();
		String hql = "SELECT m FROM Mascotas m WHERE m.masEdad>= '3'";
		Query query = session.createQuery(hql);
		List<Mascotas> lasMascotas = query.list();

		for (Mascotas mascotas : lasMascotas) {
			log.info("" + mascotas.getMasCodigo());
			log.info("" +mascotas.getMasEdad());
			log.info(mascotas.getMasNombre());
			log.info("" + mascotas.getClientes());
		}
		session.close();
	}
	
	@Test
	public void kConsultarClientesConMasDeTresMascotas(){
		Session session = sessionFactory.openSession();
		String hql = "SELECT c FROM Clientes c WHERE c.mascotases.size >= 3";
		Query query = session.createQuery(hql);
		List<Clientes> losClientes = query.list();

		for (Clientes clientes : losClientes) {
			log.info("" + clientes.getCliNombre());
		}
		session.close();
	}
	@Test
	public void lConsultarMascotasConLetraP(){
		Session session = sessionFactory.openSession();
		String hql = "SELECT m FROM Mascotas m WHERE m.masNombre like '%P%'";
		Query query = session.createQuery(hql);
		List<Mascotas> lasMascotas = query.list();

		for (Mascotas mascotas : lasMascotas) {
			log.info("" + mascotas.getMasNombre());
		}
		session.close();
	}
	
	@Test
	public void mConsultarMascotaMasEdad(){
		Session session = sessionFactory.openSession();
		String hql = "SELECT m FROM Mascotas m WHERE m.masEdad = (SELECT MAX(m2.masEdad) FROM Mascotas m2)";
		Query query = session.createQuery(hql);
		List<Mascotas> lasMascotas = query.list();

		for (Mascotas mascotas : lasMascotas) {
			log.info("" + mascotas.getMasCodigo());
			log.info(mascotas.getMasNombre());
			log.info("" + mascotas.getMasEdad());
		}
		session.close();	
	}
	
	@Test
	public void nConsultarPromedioEdadMascotas(){
		Session session = sessionFactory.openSession();
		String hql = "SELECT AVG(m.masEdad) FROM Mascotas m";
		Query query = session.createQuery(hql);
		double promedio = (double) query.uniqueResult();
			log.info("" + promedio);	
		
		session.close();
	}
		
		
	}
