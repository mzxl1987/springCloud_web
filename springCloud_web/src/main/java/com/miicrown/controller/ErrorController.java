package com.miicrown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/error")
public class ErrorController {

	@RequestMapping(value="/", method = RequestMethod.GET)
	public Object error(){
		
		
		
		return "error";
	}
	
}
