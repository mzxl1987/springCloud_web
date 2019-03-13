package com.miicrown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController
@Controller
@RequestMapping(path="/test")
public class TestController {

	private static String helloAnyone = "Hello, %s !";
	
	@RequestMapping(path="/hello/{name}")
	@ResponseBody
	public Object hello(@PathVariable(value="name") String name){
		System.out.println("hello");
		return String.format(helloAnyone, name);
	}
	
	@RequestMapping(path="/helloPage")
	public Object hello(){
		System.out.println("Hello Page");
		return "hello";
	}
	
	
}
