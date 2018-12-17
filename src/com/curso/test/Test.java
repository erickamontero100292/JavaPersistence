package com.curso.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.curso.domain.Avaluo;
import com.curso.domain.DiarioCliente;
import com.curso.domain.Tramite;
import com.curso.domain.Tramite_;
import com.curso.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// consultCriteriaPersonalizadaTramite();
		// consultCriteriaTramite();
		// methodUpdateOrSaveUpdate();
		// insertAvaluo();
		// consultRegistreOneToOne();
//		updatetRegistreOneToOne();
		useOneToManyAndManyToOne();
	}

	private static void insertTramite() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		// Creo una instancia de tramite

		Tramite tramite = new Tramite("Avaluo", new Timestamp(date.getTime()));

		// salvar el tramite
		session.save(tramite);
		session.getTransaction().commit();

		session.close();
	}

	private static void insertAvaluo() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			Date date = new Date();
			Tramite tramite = new Tramite("Prestamo", new Timestamp(date.getTime()));

			session.saveOrUpdate(tramite);

			Avaluo avaluo = new Avaluo("Norte 2");
			avaluo.setTramite(tramite);
			session.save(avaluo);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();

		}
	}

	private static void consultTramite() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Tramite> query = session.createQuery("from Tramite where tipoTram = :tipoTram");
		query.setParameter("tipoTram", "Credito");
		List<Tramite> tramites = query.getResultList();
		System.out.println(tramites.toString());
		session.getTransaction().commit();

		session.close();
	}

	private static void consultCriteriaTramite() {
		// criteria son consultas de tipo seguro
		// usan interfaces
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// Fabrica para las piezas individuales de la criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
		// Definir el tipo de entidad que reronar la consulta
		Root<Tramite> root = criteria.from(Tramite.class);
		// Construyendo la consulta
		criteria.select(root);
		List<Tramite> tramites = session.createQuery(criteria).getResultList();
		System.out.println(tramites.toString());
		session.getTransaction().commit();
		session.close();
	}

	private static void consultCriteriaPersonalizadaTramite() {
		// criteria son consultas de tipo seguro
		// usan interfaces
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// Fabrica para las piezas individuales de la criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
		// Definir el tipo de entidad que reronar la consulta
		Root<Tramite> root = criteria.from(Tramite.class);
		// Construyendo la consulta
		criteria.select(root);
		criteria.where(builder.equal(root.get(Tramite_.tipoTram), "Credito"));
		List<Tramite> tramites = session.createQuery(criteria).getResultList();
		System.out.println(tramites.toString());
		session.getTransaction().commit();
		session.close();
	}

	private static Tramite loadTramite(String type) {
		// criteria son consultas de tipo seguro
		// usan interfaces
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// Fabrica para las piezas individuales de la criteria
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
		// Definir el tipo de entidad que reronar la consulta
		Root<Tramite> root = criteria.from(Tramite.class);
		// Construyendo la consulta
		criteria.select(root);
		criteria.where(builder.equal(root.get(Tramite_.tipoTram), type));
		Tramite tramite = session.createQuery(criteria).getSingleResult();
		session.getTransaction().commit();
		session.close();
		return tramite;
	}

	private static void methodUpdateOrSaveUpdate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			// Busco el tramite
			Tramite tramite = loadTramite("Credito2");
			System.out.println(tramite);
			tramite.setTipoTram("Otro tramite");
			session.update(tramite);
			System.out.println(tramite);

			Date date = new Date();
			// Creo una instancia de tramite

			Tramite tramite2 = new Tramite("Credito nuevo", new Timestamp(date.getTime()));

			session.saveOrUpdate(tramite);
			session.saveOrUpdate(tramite2);
			session.delete(tramite);

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();

		}
	}

	private static void consultTramiteByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Tramite> query = session.createQuery("from Tramite where tipoTram = :tipoTram");
		query.setParameter("tipoTram", name);
		List<Tramite> tramites = query.getResultList();
		System.out.println(tramites.toString());
		session.getTransaction().commit();

		session.close();
	}

	// Consulta de registros con clases anotadas con @OneToOne
	// Sección 2, Clase 22
	private static void consultRegistreOneToOne() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			// Consultar el tramite de un Avaluo
			Avaluo avaluo = session.load(Avaluo.class, 2);
			Tramite tramite = avaluo.getTramite();
			System.out.println(tramite);

			// CONSULTAR TODOS LOS TRAMITES DE LOS AVALUOS
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Avaluo> criteria = builder.createQuery(Avaluo.class);
			Root<Avaluo> root = criteria.from(Avaluo.class);
			criteria.select(root);

			List<Avaluo> list = session.createQuery(criteria).getResultList();

			System.out.println("Lista de tramites");

			for (Avaluo aval : list) {
				System.out.println("Tramite " + aval.getTramite());
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();

		}
	}
//	Actualizaciones en clases anotadas con @OneToOne
//	Sección 2, Clase 23

	private static void updatetRegistreOneToOne() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			// Consultar un tramite
			Tramite tramite = session.load(Tramite.class, 1);
			Avaluo avaluo = tramite.getAvaluo();
			System.out.println(avaluo);

//			avaluo.setLugarAval("Guarenas");			
			// session.update(avaluo);
//			session.delete(avaluo);
//			System.out.println(avaluo);
			session.delete(tramite);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();

		}
	}
	
	private static void insertTramite(String name) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		// Creo una instancia de tramite

		Tramite tramite = new Tramite(name, new Timestamp(date.getTime()));

		// salvar el tramite
		session.save(tramite);
		session.getTransaction().commit();

		session.close();
	}
	
//	Uso @OneToMany y @ManyToOne
//	Sección 2, Clase 25

	private static void useOneToManyAndManyToOne() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Date date = new Date();
			Timestamp time =new Timestamp(date.getTime());
			// Creo una instancia de tramite

			Tramite tramite = new Tramite("Credito", time);
			Tramite tramite2 = new Tramite("Prestamo", time);
			session.save(tramite);
			session.save(tramite2);
			DiarioCliente diario = new DiarioCliente("Entrada 1", time, tramite2);
			DiarioCliente diario2 = new DiarioCliente("Entrada 2", time, tramite2);
			DiarioCliente diario3 = new DiarioCliente("Entrada 3", time, tramite);
			
			session.save(diario);
			session.save(diario2);
			session.save(diario3);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();

		}
	}
	
}
