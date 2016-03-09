package com.Picker.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.Picker.model.User;

public class Database {

	private SessionFactory factory;
	
	public Database() {
		factory = HibernateUtilities.getSessionFactory();
	}
	
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
