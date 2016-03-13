package com.Picker.actions;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.interceptor.SessionAware;

import com.Picker.data.Database;
import com.Picker.model.User;

import com.opensymphony.xwork2.validator.annotations.*;

/*
 * Purpose of this action class is to handle form input for
 * the application's login page.
 */

@InterceptorRef(value="defaultStack")
public class Login extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private boolean postBack;
	private String enteredUsername;
	private String enteredPassword;
	private static Database db = new Database();
	private Map<String, Object> session;
	
	/*
	 * Input action to avoid showing field validation errors on first load(non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	@Action("login-input")
	public String input()
	{
		return "login";
	}
	
	/*
	 * If the form has been submitted, check entered credentials against
	 * the user in the database and return the authentication result.
	 * Otherwise, simply load the page.
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	@Action("login")
	public String execute() {
		if (postBack) {
			return authenticate();
		}
		return SUCCESS;
	}
	
	/*
	 * Checks if the provided username and password are valid.
	 * If they are, return "redirect" which will direct the page to 
	 * the welcome screen.
	 * Otherwise, add an action error and return ajax to display error
	 * to the user.
	 */
	public String authenticate() {
		boolean success = db.authenticateUser(enteredUsername, enteredPassword);
		if (success) {
			User user = db.getUser(enteredUsername);
			session.put("currentUser", user);
			return "toWelcome";
		} else {
			addActionError("Invalid user credentials!");
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
