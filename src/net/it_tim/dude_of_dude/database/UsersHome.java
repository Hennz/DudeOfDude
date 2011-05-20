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
 * 
 * @see net.it_tim.dude_of_dude.database.Users
 * @author Hibernate Tools
 */
public class UsersHome extends DAO {

	private static final Log log = LogFactory.getLog(UsersHome.class);

	public void persist(Users transientInstance) {
		log.debug("persisting Users instance");
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

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
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
	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
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

	public void update(Users instance) {
		log.debug("updating Users instance");
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

	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
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

	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			begin();
			Users result = (Users) getCurrentSession().merge(detachedInstance);
			commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			rollback();
			log.error("merge failed", re);
			throw re;
		}
	}

	public Users findById(int id) {
		log.debug("getting Users instance with id: " + id);
		try {
			begin();
			Users instance = (Users) getCurrentSession().get(
					"net.it_tim.dude_of_dude.database.Users", id);
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
	public List<Users> findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			begin();
			List<Users> results = (List<Users>) getCurrentSession()
					.createCriteria("net.it_tim.dude_of_dude.database.Users")
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

	public boolean checkPassword(String login, String password) {
		begin();
		Users user = (Users) getCurrentSession().createCriteria(
				"net.it_tim.dude_of_dude.database.Users").add(
				Restrictions.eq("login", login)).uniqueResult();
		commit();
		String currHash = new Md5Hash(password).toString();
		try {
			if (currHash.equals(user.getPassword())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List getAll() {
		try {
			begin();
			List group_list = getCurrentSession().createQuery(
					"from Users order by name asc").list();
			commit();
			return group_list;
		} catch (RuntimeException re) {
			rollback();
			return null;
		}
	}
}
