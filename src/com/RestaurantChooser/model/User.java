package com.RestaurantChooser.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/*
 * This is the object model for a user of the application.
 * Self-explanatory and mapped to the database using hibernate annotations.
 */

@Entity
@Table(name="picker_users")
public class User {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String id;
	
	public User() {}
	
	@Override
	public boolean equals(Object u) {
		return this.id.equals(((User)u).getId());
	}
	
	@Override
	public String toString() {
		return "Username: "+username+", Name: "+firstName+" "+lastName+", Password: "+password;
	}
	
	@Column(name="username", unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
	@Column(name="id", unique=true)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
