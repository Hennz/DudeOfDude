package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Home object for domain model class PingHistory.
 * 
 * @see net.it_tim.dude_of_dude.database.PingHistory
 * @author Hibernate Tools
 */
public class PingHistoryHome extends DatabaseHome {

	private static final Log log = LogFactory.getLog(PingHistoryHome.class);

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

	public PingHistory getLastState(Hosts host) {
		Query query = getCurrentSession()
				.createSQLQuery(
						"SELECT * from ping_history where id = (SELECT max(id) from ping_history where host_id = :hostId)")
				.addEntity(PingHistory.class).setParameter("hostId",
						host.getHostId());
		PingHistory ph = (PingHistory) query.uniqueResult();
		return ph;
	}
}
