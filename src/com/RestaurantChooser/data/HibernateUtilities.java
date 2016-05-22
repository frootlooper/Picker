package com.RestaurantChooser.data;

/*
 * This utility uses hibernate to create a database session factory
 * given the configuration file and data model classes.
 */
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtilities {

	private static SessionFactory sessionFactory;
	
	static
	{
		try
		{
			Configuration configuration = new Configuration()
					.addAnnotatedClass(com.RestaurantChooser.model.User.class)
					.configure("hibernate.cfg.xml");
			
			StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
		}
		catch(HibernateException exception)
		{
			System.out.println("Problem creating session factory!");
			System.out.println(exception.getLocalizedMessage());
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
