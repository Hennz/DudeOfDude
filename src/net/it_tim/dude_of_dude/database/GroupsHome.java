package net.it_tim.dude_of_dude.database;

// Generated 20 квіт 2011 10:24:31 by Hibernate Tools 3.3.0.GA

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;

/**
 * Home object for domain model class Groups.
 * 
 * @see net.it_tim.dude_of_dude.database.Groups
 * @author Hibernate Tools
 */
public class GroupsHome extends DAO {

	private static final Log log = LogFactory.getLog(GroupsHome.class);

	public void persist(Groups transientInstance) {
		log.debug("persisting Groups instance");
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

	public void attachDirty(Groups instance) {
		log.debug("attaching dirty Groups instance");
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
	public void attachClean(Groups instance) {
		log.debug("attaching clean Groups instance");
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

	public void update(Groups instance) {
		log.debug("updating dirty Groups instance");
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

	public void delete(Groups persistentInstance) {
		log.debug("deleting Groups instance");
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

	public Groups merge(Groups detachedInstance) {
		log.debug("merging Groups instance");
		try {
			begin();
			Groups result = (Groups) getCurrentSession()
					.merge(detachedInstance);
			commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			rollback();
			log.error("merge failed", re);
			throw re;
		}
	}

	public Groups findById(int id) {
		log.debug("getting Groups instance with id: " + id);
		try {
			begin();
			Groups instance = (Groups) getCurrentSession().get(
					"net.it_tim.dude_of_dude.database.Groups", id);
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
	public List<Groups> findByExample(Groups instance) {
		log.debug("finding Groups instance by example");
		try {
			begin();
			List<Groups> results = (List<Groups>) getCurrentSession()
					.createCriteria("net.it_tim.dude_of_dude.database.Groups")
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

	@SuppressWarnings("unchecked")
	public List getAll() {
		try {
			begin();
			List group_list = getCurrentSession().createQuery(
					"from Groups order by description asc").list();
			commit();
			return group_list;
		} catch (RuntimeException re) {
			rollback();
			return null;
		}
	}
}
