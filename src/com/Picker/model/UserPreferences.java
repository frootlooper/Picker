package com.Picker.model;

import javax.persistence.*;

@Entity
@Table(name="picker_user_preferences")
public class UserPreferences {
	private User user;
	private ZipCode zip;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public ZipCode getZip() {
		return zip;
	}
	
	public void setZip(ZipCode zip) {
		this.zip = zip;
	}
	
}
