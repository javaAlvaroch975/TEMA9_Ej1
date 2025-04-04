package com.hibernate.dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hibernate.model.Ciudad;
import com.hibernate.util.HibernateUtil;
	public class CiudadDao {

	//INSERTAR
	public void insertCiudad(Ciudad p) { //hibernate
		Transaction transaction=null; //hibernate
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.persist(p);
			transaction.commit();
		}catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
	}
	
	//ACTUALIZAR
	public void updateCiudad(Ciudad p) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.merge(p);
			transaction.commit();
		}catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
	}
	
	//BORRAR
	public void deleteCiudad(int id) {
		Transaction transaction=null;
		Ciudad p=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			p=session.get(Ciudad.class, id);
			session.remove(p);
			transaction.commit();
		}catch (Exception e) {
			if (transaction!=null){
				transaction.rollback();
			}
		}
	}
	
	//SELECCION SIMPLE
	public Ciudad selectCiudadBYID(int id) {
		Transaction transaction=null;
		Ciudad p=null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			p=session.get(Ciudad.class, id);
			transaction.commit();
		}catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
		return p;
	}
	
	//SELECCION MULTIPLE
	public List<Ciudad> selectAllCiudad(){ //util
		Transaction transaction=null;
		List<Ciudad> personas = null;
		Ciudad p = null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			personas=session.createQuery("from Ciudad", Ciudad.class).getResultList();
			transaction.commit();
		}catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}
		return personas;
	}
}

