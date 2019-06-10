package com.miicrown.service;

import javax.servlet.http.HttpServletRequest;

import com.miicrown.entity.User;

public interface ApiService {
	
	public Object authLogin(User user, HttpServletRequest request);
	public Object permission(User user, HttpServletRequest request);
	public Object auth2stepCode(User user);
	
}
