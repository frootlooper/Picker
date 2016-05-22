package com.RestaurantChooser.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;

	@Override
	@Action("logout")
	public String execute() {
		session.remove("currentUser");
		return "nologin";
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
