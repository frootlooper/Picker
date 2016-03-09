package com.Picker.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckLogin implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("Action name: "+invocation.getAction().getClass().getName());
        //return invocation.invoke();
		return "login";
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
