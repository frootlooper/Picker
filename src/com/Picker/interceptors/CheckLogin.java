package com.Picker.interceptors;

import java.util.Map;

import com.Picker.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/*
 * Purpose of this custom interceptor is to check if the user
 * is logged in. If not, then redirect to the login action.
 */
public class CheckLogin implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("Action name: "+invocation.getAction().getClass().getName());
        //return invocation.invoke();
		
        Map<String, Object> sessionAttributes = invocation.getInvocationContext().getSession();
        User user = (User) sessionAttributes.get("currentUser");
        System.out.println(user);
        
        if (user == null) {
        	return "nologin";
        }
		
		return invocation.invoke();
	}
	
	@Override
	public void destroy() {
		System.out.println("CheckLogin destroy() is called...");
	}

	@Override
	public void init() {
		System.out.println("CheckLogin init() is called...");
	}
}
