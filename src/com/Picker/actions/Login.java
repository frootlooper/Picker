package com.Picker.actions;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.Picker.data.Database;
import com.Picker.model.User;

import com.opensymphony.xwork2.validator.annotations.*;

@InterceptorRef(value="defaultStack")
@Results(
	    @Result(name="redirect", location="index.ftl")
	    )
public class Login extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean postBack;
	private String enteredUsername;
	private String enteredPassword;
	private static Database db = new Database();
	private Map<String, Object> session;
	
	@Action("login-input")
	public String input()
	{
		System.out.println("In login-input action");
		return "login";
	}
	
	@Override
	@Action("login")
	public String execute() {
		if (postBack) {
			return authenticate();
		}
		return SUCCESS;
	}
	
	public String authenticate() {
		System.out.println("in authenticate function");
		boolean success = db.authenticateUser(enteredUsername, enteredPassword);
		if (success) {
			System.out.println("Authentication a success");
			User user = db.getUser(enteredUsername);
			session.put("currentUser", user);
			return "redirect";
		} else {
			addActionError("Login credentials invalid.");
			return "ajax";
		}
	}

	public boolean isPostBack() {
		return postBack;
	}

	public void setPostBack(boolean postBack) {
		this.postBack = postBack;
	}

	public String getEnteredUsername() {
		return enteredUsername;
	}

	@RequiredStringValidator(message="You must enter a username.")
	public void setEnteredUsername(String enteredUsername) {
		this.enteredUsername = enteredUsername;
	}

	public String getEnteredPassword() {
		return enteredPassword;
	}

	@RequiredStringValidator(message="You must enter a password.")
	public void setEnteredPassword(String enteredPassword) {
		this.enteredPassword = enteredPassword;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
