package net.it_tim.dude_of_dude.db_classes;

// Generated 14 квіт 2011 15:28:49 by Hibernate Tools 3.3.0.GA

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PingHistory.
 * @see net.it_tim.dude_of_dude.db_classes.PingHistory
 * @author Hibernate Tools
 */
public class PingHistoryHome {

	private static final Log log = LogFactory.getLog(PingHistoryHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(PingHistory transientInstance) {
		log.debug("persisting PingHistory instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PingHistory instance) {
		log.debug("attaching dirty PingHistory instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PingHistory instance) {
		log.debug("attaching clean PingHistory instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PingHistory persistentInstance) {
		log.debug("deleting PingHistory instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PingHistory merge(PingHistory detachedInstance) {
		log.debug("merging PingHistory instance");
		try {
			PingHistory result = (PingHistory) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PingHistory findById(int id) {
		log.debug("getting PingHistory instance with id: " + id);
		try {
			PingHistory instance = (PingHistory) sessionFactory
					.getCurrentSession().get(
							"net.it_tim.dude_of_dude.db_classes.PingHistory",
							id);
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

	public List<PingHistory> findByExample(PingHistory instance) {
		log.debug("finding PingHistory instance by example");
		try {
			List<PingHistory> results = (List<PingHistory>) sessionFactory
					.getCurrentSession().createCriteria(
							"net.it_tim.dude_of_dude.db_classes.PingHistory")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
