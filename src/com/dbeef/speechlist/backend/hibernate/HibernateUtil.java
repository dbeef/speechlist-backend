package com.dbeef.speechlist.backend.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			
			StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
			standardServiceRegistryBuilder.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();

			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getHibernateSession() {

		final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		
		// factory = new Configuration().configure().buildSessionFactory();
		final Session session = sf.openSession();
		return session;
	}
}