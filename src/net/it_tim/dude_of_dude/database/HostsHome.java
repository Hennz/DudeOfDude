package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;

/**
 * Home object for domain model class Hosts.
 * 
 * @see net.it_tim.dude_of_dude.database.Hosts
 * @author Hibernate Tools
 */
public class HostsHome extends DAO {

	private static final Log log = LogFactory.getLog(HostsHome.class);

	public void persist(Hosts transientInstance) {
		log.debug("persisting Hosts instance");
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

	public void attachDirty(Hosts instance) {
		log.debug("attaching dirty Hosts instance");
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

	public void update(Hosts instance) {
		log.debug("updating dirty Hosts instance");
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

	@SuppressWarnings("deprecation")
	public void attachClean(Hosts instance) {
		log.debug("attaching clean Hosts instance");
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

	public void delete(Hosts persistentInstance) {
		log.debug("deleting Hosts instance");
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

	public Hosts merge(Hosts detachedInstance) {
		log.debug("merging Hosts instance");
		try {
			Hosts result = (Hosts) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Hosts findById(int id) {
		log.debug("getting Hosts instance with id: " + id);
		try {
			begin();
			Hosts instance = (Hosts) getCurrentSession().get(
					"net.it_tim.dude_of_dude.database.Hosts", id);
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
	public List<Hosts> findByExample(Hosts instance) {
		log.debug("finding Hosts instance by example");
		try {
			begin();
			List<Hosts> results = (List<Hosts>) getCurrentSession()
					.createCriteria("net.it_tim.dude_of_dude.database.Hosts")
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

	public Hosts findByIp(String ipAddr) {
		log.debug("finding Hosts instance by IP: " + ipAddr);
		try {
			begin();
			Hosts instance = (Hosts) getCurrentSession().createCriteria(
					"net.it_tim.dude_of_dude.database.Hosts").add(
					Restrictions.eq("ipAdres", ipAddr)).uniqueResult();
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
	public List getAll() {
		try {
			begin();
			List host_list = getCurrentSession().createQuery(
					"from Hosts order by ipAdres asc").list();
			commit();
			return host_list;
		} catch (RuntimeException re) {
			rollback();
			return null;
		}
	}
}
