package com.miicrown.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.miicrown.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
//@RequestMapping(path="/")
@Api(value="Home Controller")
public class HomeController {

	private static String helloAnyone = "Hello, %s !";
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(path={"/","/dashboard"}, method=RequestMethod.GET)
	public Object home(){
		return "index";
	}
	
	@RequestMapping(path="/hello/{name}", method = RequestMethod.POST)
	@ResponseBody
	public Object hello(@PathVariable(value="name") String name) throws Exception{
		
		testService.saveUser();
		
		return String.format(helloAnyone, name);
	}
	
	@ApiOperation(value="跳转页面", notes="带有参数的页面跳转")
	@ApiImplicitParams({
		@ApiImplicitParam(value="用户名", name="username", required=true)
	})
	@RequestMapping(path="/helloPage/{username}", method = RequestMethod.GET)
	public Object hello(@PathVariable(value="username") String username, ModelMap map,HttpServletRequest request) throws Exception{
		
		map.addAttribute("users", testService.findUsers(username));
		
		return "hello";
	}
	
	@RequestMapping(path="/toRead", method = RequestMethod.GET)
	@ResponseBody
	public Object toRead(){
		return testService.readingList(); 
	}
	
	
}
