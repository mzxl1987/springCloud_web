package com.miicrown.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.miicrown.service.TestService;

@Controller
@RequestMapping(path="/test")
public class TestController {

	private static String helloAnyone = "Hello, %s !";
	
	@Autowired
	private TestService testService;
		
	@RequestMapping(path="/hello/{name}", method = RequestMethod.POST)
	@ResponseBody
	public Object hello(@PathVariable(value="name") String name) throws Exception{
		
		testService.saveUser();
		
		return String.format(helloAnyone, name);
	}
	
	@RequestMapping(path="/helloPage", method = RequestMethod.GET)
	public Object hello(){
		return "hello";
	}
	
	@RequestMapping(path="/toRead", method = RequestMethod.GET)
	@ResponseBody
	public Object toRead(){
		return testService.readingList(); 
	}
	
	
}
