package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

/**
 * Home object for domain model class NotificatioinsHistory.
 * 
 * @see net.it_tim.dude_of_dude.database.NotificatioinsHistory
 * @author Hibernate Tools
 */
public class NotificatioinsHistoryHome extends DAO {

	private static final Log log = LogFactory
			.getLog(NotificatioinsHistoryHome.class);

	public void notificate(NotificatioinsHistory transientInstance) {
		log.debug("persisting NotificatioinsHistory instance");
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
}
