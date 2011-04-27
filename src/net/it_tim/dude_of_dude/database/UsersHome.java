package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import java.util.List;

import net.it_tim.dude_of_dude.Md5Hash;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Users.
 * @see net.it_tim.dude_of_dude.database.Users
 * @author Hibernate Tools
 */
public class UsersHome extends DAO {

	private static final Log log = LogFactory.getLog(UsersHome.class);

	public void persist(Users transientInstance) {
		log.debug("persisting Users instance");
		try {
			getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Users findById(int id) {
		log.debug("getting Users instance with id: " + id);
		try {
			Users instance = (Users) getCurrentSession().get(
					"net.it_tim.dude_of_dude.database.Users", id);
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
	public List<Users> findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			List<Users> results = (List<Users>) getCurrentSession().createCriteria(
							"net.it_tim.dude_of_dude.database.Users").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public boolean checkPassword( String login, String password ) {
		Users user = (Users) getCurrentSession().createCriteria("net.it_tim.dude_of_dude.database.Users")
		.add(Restrictions.eq("login", login)).uniqueResult();
		
		String currHash = new Md5Hash(password).toString();
		try {
		if (currHash.equals(user.getPassword())) { return true; } else { return false; }
		} catch (Exception ex) { return false; }
	}
}
