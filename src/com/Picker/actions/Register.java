package com.Picker.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.Picker.data.Database;
import com.Picker.model.User;

/*
 * The purpose of this action class is to handle creation of a
 * new user.
 */

@InterceptorRef(value="defaultStack")
@Results(
	    @Result(name="registerSuccess", location="registerConfirmation.ftl")
	    )
public class Register extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private boolean postBack;
	private String enteredUsername;
	private String enteredFirstName;
	private String enteredLastName;
	private String enteredPassword;
	private static Database db = new Database();
	private static StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

	/*
	 * Input action to avoid showing validation errors on first load
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	@Action("register-input")
	public String input()
	{
		return "register";
	}
	
	/*
	 * If the form has been submitted, check if the user was created successully.
	 * Otherwise, just load form.
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	@Action("register")
	public String execute() {
		if (isPostBack()) {
			return registerUser();
		}
		return SUCCESS;
	}
	
	/*
	 * Attempts to register the user.
	 * Returns ajax if there was a problem.
	 * Returns registerSuccess if success.
	 */
	public String registerUser() {
		boolean exists = db.usernameExists(enteredUsername);
		if (exists) {
			addActionError("Username already exists");
			return "register";
		} else {
			//Create user object
			User user = new User();
			user.setFirstName(enteredFirstName);
			user.setLastName(enteredLastName);
			user.setUsername(enteredUsername);
			String encryptedPassword = passwordEncryptor.encryptPassword(enteredPassword);
			user.setPassword(encryptedPassword);
			
			//Add new user to the database
			db.addUser(user);
			
			return "registerSuccess";
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
	@RegexFieldValidator(key="regex.field", regex="^[a-zA-Z0-9_]*$", message="Username must be alphanumeric.")
	public void setEnteredUsername(String enteredUsername) {
		this.enteredUsername = enteredUsername;
	}

	public String getEnteredFirstName() {
		return enteredFirstName;
	}

	@RequiredStringValidator(message="You must enter a first name.")
	@RegexFieldValidator(key="regex.field", regex="^[a-zA-Z_]*$", message="First name must be made of letters.")
	public void setEnteredFirstName(String enteredFirstName) {
		this.enteredFirstName = enteredFirstName;
	}

	public String getEnteredLastName() {
		return enteredLastName;
	}

	@RequiredStringValidator(message="You must enter a last name.")
	@RegexFieldValidator(key="regex.field", regex="^[a-zA-Z_]*$", message="Last name must be made of letters.")
	public void setEnteredLastName(String enteredLastName) {
		this.enteredLastName = enteredLastName;
	}

	public String getEnteredPassword() {
		return enteredPassword;
	}

	@RequiredStringValidator(message="You must enter a password.")
	@RegexFieldValidator(key="regex.field", regex="^[a-zA-Z0-9_]*$", message="Password must be alphanumeric.")
	public void setEnteredPassword(String enteredPassword) {
		this.enteredPassword = enteredPassword;
	}
}
