package net.it_tim.dude_of_dude.database;

import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAO {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getAnonymousLogger();
	@SuppressWarnings("unchecked")
	private static final ThreadLocal session = new ThreadLocal();
	private static final SessionFactory sessionFactory = new Configuration()
			.configure().buildSessionFactory();

	protected DAO() {
	}

	@SuppressWarnings("unchecked")
	public static Session getCurrentSession() {
		Session session = (Session) DAO.session.get();
		if (session == null) {
			session = sessionFactory.openSession();
			DAO.session.set(session);
		}
		return session;
	}

	protected void begin() {
		getCurrentSession().beginTransaction();
	}

	protected void commit() {
		getCurrentSession().getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	protected void rollback() {
		try {
			getCurrentSession().getTransaction().rollback();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		try {
			getCurrentSession().close();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		DAO.session.set(null);
	}

	@SuppressWarnings("unchecked")
	public static void close() {
		getCurrentSession().close();
		DAO.session.set(null);
	}
}