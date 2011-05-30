package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Home object for domain model class Hosts.
 * 
 * @see net.it_tim.dude_of_dude.database.Hosts
 * @author Hibernate Tools
 */
public class HostsHome extends DatabaseHome {

	private static final Log log = LogFactory.getLog(HostsHome.class);

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
