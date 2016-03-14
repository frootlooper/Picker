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
		
		User u = new User();
		u.setFirstName("Gwen");
		u.setLastName("M");
		u.setUsername("gwenm");
		u.setPassword("test");
		u.setId("d75e6885-9357-481b-9a23-882cc3f05bd6");
		
		Database db = new Database();		
		db.updateUser(u);
	}	
}
