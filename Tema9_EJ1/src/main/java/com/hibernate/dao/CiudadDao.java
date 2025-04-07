package com.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.model.Ciudad;
import com.hibernate.util.HibernateUtil;

public class CiudadDao {

	// INSERTAR
	public void insertCiudad(Ciudad p) { // hibernate
		Transaction transaction = null; // hibernate
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	// ACTUALIZAR
	public void updateCiudad(Ciudad p) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	// BORRAR
	public void deleteCiudad(int id) {
		Transaction transaction = null;
		Ciudad p = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			p = session.get(Ciudad.class, id);
			session.remove(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	// SELECCION SIMPLE
	public Ciudad selectCiudadBYID(int id) {
		Transaction transaction = null;
		Ciudad c = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			c = session.get(Ciudad.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return c;
	}

	// SELECCION MULTIPLE
	public List<Ciudad> selectAllCiudad() { // util
		Transaction transaction = null;
		List<Ciudad> all_cities = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			all_cities = session.createQuery("from Ciudad", Ciudad.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return all_cities;
	}

	// CASE 9
	public List<Ciudad> selectAll1M() { // util
		Transaction transaction = null;
		List<Ciudad> ciudades_1M = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ciudades_1M = session.createQuery("FROM Ciudad WHERE num_habitantes > 1000000", Ciudad.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return ciudades_1M;
	}

	// CASE 8
	public List<Ciudad> selectAllmenosuser(int numHab) { // util
		Transaction transaction = null;
		List<Ciudad> num_hab = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Ciudad> query = session.createQuery("FROM Ciudad WHERE num_habitantes < :num_hab_user", Ciudad.class);
			query.setParameter("num_hab_user", numHab);
			num_hab = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return num_hab;
	}

	// BUSQUEDA
	public Ciudad selectCiudadBYNombre(String nombre) {
		Transaction transaction = null;
		Ciudad c = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Ciudad> query = session.createQuery("FROM Ciudad WHERE nombre = :nombre", Ciudad.class);
			// tipo clase nombre
			query.setParameter("nombre", nombre);
			c = query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return c;
	}

}
