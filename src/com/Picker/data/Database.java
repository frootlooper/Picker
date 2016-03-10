package com.Picker.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.Picker.model.User;

/*
 * The purpose of this class is to handle all database connections
 * using the HibernateUtilies.
 */
public class Database {

	private SessionFactory factory;
	
	/*
	 * Constructor to initialize the database session factory using
	 * the hibernateUtilities class which contains configuration info.
	 */
	public Database() {
		factory = HibernateUtilities.getSessionFactory();
	}
	
	/*
	 * Add the user to the database as a new record.
	 */
	public void addUser(User user) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
	
	/*
	 * Check that a row exists that contains the given username and
	 * the encrypted version of the entered password.
	 * Returns true if success.
	 * Return false otherwise.
	 */
	public boolean authenticateUser(String username, String password) {
		Session session = factory.openSession();
		boolean success = false;
		try {
			User u = (User) session.createCriteria(com.Picker.model.User.class)
					.add(Restrictions.eq("username", username))
					.list().get(0);
			String dbpassword= u.getPassword();
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			success = passwordEncryptor.checkPassword(password, dbpassword);
		} catch (Exception e) {
			//Authentication failed for various reasons
		} finally {
			session.close();
		}
		return success;
	}
	
	/*
	 * Get all user information from the database given their username.
	 * Returns user object.
	 * Object is null if the user did not exist or other error occurred. 
	 */
	public User getUser(String username) {
		User u = null;
		Session session = factory.openSession();
		try {
			u = (User) session.createCriteria(com.Picker.model.User.class)
					.add(Restrictions.eq("username", username))
					.list().get(0);
		} catch (Exception e) {
			//User didn't exist, etc.
		} finally {
			session.close();
		}
		return u;
	}
	
	/*
	 * Checks to see if username exists in the database.
	 * Returns true if so.
	 * Returns false otherwise.
	 */
	public boolean usernameExists(String username) {
		boolean result = false;
		Session session = factory.openSession();
		try {
			long count = (long) session.createCriteria(com.Picker.model.User.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.eq("username", username))
				.uniqueResult();
			if (count > 0) {
				result = true;
			}
		} finally {
			session.close();
		}
		return result;
	}
}
