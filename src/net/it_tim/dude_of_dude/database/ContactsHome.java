package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Contacts.
 * @see net.it_tim.dude_of_dude.database.Contacts
 * @author Hibernate Tools
 */
public class ContactsHome extends DAO {

	private static final Log log = LogFactory.getLog(ContactsHome.class);

	public void persist(Contacts transientInstance) {
		log.debug("persisting Contacts instance");
		try {
			getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Contacts instance) {
		log.debug("attaching dirty Contacts instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Contacts instance) {
		log.debug("attaching clean Contacts instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Contacts persistentInstance) {
		log.debug("deleting Contacts instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contacts merge(Contacts detachedInstance) {
		log.debug("merging Contacts instance");
		try {
			Contacts result = (Contacts) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Contacts findById(int id) {
		log.debug("getting Contacts instance with id: " + id);
		try {
			Contacts instance = (Contacts) getCurrentSession()
					.get("net.it_tim.dude_of_dude.database.Contacts", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contacts> findByExample(Contacts instance) {
		log.debug("finding Contacts instance by example");
		try {
			List<Contacts> results = (List<Contacts>) 
					getCurrentSession().createCriteria(
							"net.it_tim.dude_of_dude.database.Contacts").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
