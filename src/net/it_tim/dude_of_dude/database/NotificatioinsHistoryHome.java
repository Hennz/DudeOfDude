package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class NotificatioinsHistory.
 * @see net.it_tim.dude_of_dude.database.NotificatioinsHistory
 * @author Hibernate Tools
 */
public class NotificatioinsHistoryHome extends DAO {

	private static final Log log = LogFactory
			.getLog(NotificatioinsHistoryHome.class);

	public void persist(NotificatioinsHistory transientInstance) {
		log.debug("persisting NotificatioinsHistory instance");
		try {
			getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(NotificatioinsHistory instance) {
		log.debug("attaching dirty NotificatioinsHistory instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(NotificatioinsHistory instance) {
		log.debug("attaching clean NotificatioinsHistory instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(NotificatioinsHistory persistentInstance) {
		log.debug("deleting NotificatioinsHistory instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NotificatioinsHistory merge(NotificatioinsHistory detachedInstance) {
		log.debug("merging NotificatioinsHistory instance");
		try {
			NotificatioinsHistory result = (NotificatioinsHistory) 
					getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public NotificatioinsHistory findById(int id) {
		log.debug("getting NotificatioinsHistory instance with id: " + id);
		try {
			NotificatioinsHistory instance = (NotificatioinsHistory) 
					getCurrentSession()
					.get(
							"net.it_tim.dude_of_dude.database.NotificatioinsHistory",
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
	public List<NotificatioinsHistory> findByExample(
			NotificatioinsHistory instance) {
		log.debug("finding NotificatioinsHistory instance by example");
		try {
			List<NotificatioinsHistory> results = (List<NotificatioinsHistory>) 
					getCurrentSession()
					.createCriteria(
							"net.it_tim.dude_of_dude.database.NotificatioinsHistory")
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
