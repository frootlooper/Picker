package com.Picker.model;

import java.io.IOException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.Picker.data.Database;
import com.Picker.data.HibernateUtilities;

public class Test {
	
	//private static String apikey = "AIzaSyD5DKLxRQV-Dd5oaN48cXsA34bICRUns7M";

	public static void main(String[] args) throws IOException {
		
		String username = "root2";
		
		SessionFactory factory = HibernateUtilities.getSessionFactory();
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			
			Database db = new Database();
			boolean success = db.usernameExists(username);
			
			session.getTransaction().commit();
			
			if (success) {
				System.out.println("user exists");
			} else {
				System.out.println("user doesn't exist");
			}
			
		} finally {
			session.close();
		}
		factory.close();
	}	
}
