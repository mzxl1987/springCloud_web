package com.miicrown.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.miicrown.entity.User;
import com.miicrown.service.ApiService;

@RestController
@RequestMapping(value="auth/")
public class AuthController {
	
	@Autowired
	ApiService apiService;

	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public Object authLogin(@ModelAttribute User obj,ModelMap modelMap, HttpServletRequest request){
		return apiService.authLogin(obj,request);
	}
	
	@RequestMapping(value="permission", method=RequestMethod.GET)
	@ResponseBody
	public Object permission(@ModelAttribute User obj,ModelMap modelMap, HttpServletRequest request){
		return apiService.permission(obj,request);
	}
	
	@RequestMapping(value="2step-code", method=RequestMethod.POST)
	@ResponseBody
	public Object auth2stepCode(@ModelAttribute User obj,ModelMap modelMap, HttpServletRequest request){
		return apiService.auth2stepCode(obj);
	}

}
