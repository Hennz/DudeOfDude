package net.it_tim.dude_of_dude.db_classes;

// Generated 14 квіт 2011 15:28:49 by Hibernate Tools 3.3.0.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class PingHistory.
 * @see net.it_tim.dude_of_dude.db_classes.PingHistory
 * @author Hibernate Tools
 */
public class PingHistoryHome extends DAO {

	private static final Log log = LogFactory.getLog(PingHistoryHome.class);

	public void persist(PingHistory transientInstance) {
		log.debug("persisting PingHistory instance");
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

	public void log_ping(PingHistory transientInstance) {
		log.debug("persisting PingHistory instance");
		try {
			begin();
			Session sesion = getCurrentSession();
			sesion.save(transientInstance);
			sesion.flush();
			sesion.clear();
			commit();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			rollback();
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void attachDirty(PingHistory instance) {
		log.debug("attaching dirty PingHistory instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(PingHistory instance) {
		log.debug("attaching clean PingHistory instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PingHistory persistentInstance) {
		log.debug("deleting PingHistory instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PingHistory merge(PingHistory detachedInstance) {
		log.debug("merging PingHistory instance");
		try {
			PingHistory result = (PingHistory) getCurrentSession().merge(detachedInstance);
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
			PingHistory instance = (PingHistory) getCurrentSession().get(
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

	@SuppressWarnings("unchecked")
	public List<PingHistory> findByExample(PingHistory instance) {
		log.debug("finding PingHistory instance by example");
		try {
			List<PingHistory> results = (List<PingHistory>) getCurrentSession().createCriteria(
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
