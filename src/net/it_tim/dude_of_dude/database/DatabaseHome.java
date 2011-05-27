package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
/**
 * Home object for domain model class Contacts.
 * 
 * @see net.it_tim.dude_of_dude.database.Contacts
 * @author Hibernate Tools
 */
public class DatabaseHome extends DAO {

	private static final Log log = LogFactory.getLog(DatabaseHome.class);

	public <T> void persist(T transientInstance) {
		log.debug("persisting Contacts instance");
		try {
			begin();
			getCurrentSession().persist(transientInstance);
			commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			rollback();
			log.error("persist failed", re);
			throw re;
		}
	}

	public <T> void update(T instance) {
		log.debug("updating dirty instance");
		try {
			begin();
			getCurrentSession().update(instance);
			commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			rollback();
			log.error("update failed", re);
			throw re;
		}
	}
	
	public <T> void attachDirty(T instance) {
		log.debug("attaching dirty Contacts instance");
		try {
			begin();
			getCurrentSession().saveOrUpdate(instance);
			commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			rollback();
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public <T> void attachClean(T instance) {
		log.debug("attaching clean Contacts instance");
		try {
			begin();
			getCurrentSession().lock(instance, LockMode.NONE);
			commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			rollback();
			log.error("attach failed", re);
			throw re;
		}
	}

	public <T> void delete(T persistentInstance) {
		log.debug("deleting Contacts instance");
		try {
			begin();
			getCurrentSession().delete(persistentInstance);
			commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			rollback();
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T merge(T detachedInstance) {
		log.debug("merging instance");
		try {
			begin();
			T result = (T) getCurrentSession().merge(detachedInstance);
			commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			rollback();
			log.error("merge failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T findById(Class<T> for_class, int id) {
		log.debug("getting instance with id: " + id);
		try {
			begin();
			T instance = (T) getCurrentSession().get(for_class, id);
			getCurrentSession().refresh(instance);
			commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			rollback();
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByExample(Class<T> for_class, Object instance) {
		log.debug("finding instance by example");
		try {
			begin();
			List<T> results = (List<T>) getCurrentSession()
					.createCriteria(for_class)
					.add(create(instance)).list();
			commit();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			rollback();
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> in_class) {
		try {
			begin();
			List<T> contact_list = (List<T>) getCurrentSession().createCriteria(in_class).list();
			commit();
			return contact_list;
		} catch (RuntimeException re) {
			rollback();
			return null;
		}
	}
}
