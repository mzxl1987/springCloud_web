package com.miicrown.service.impl;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miicrown.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TestServiceImpl implements TestService{
	
	private final RestTemplate restTemplate;
	
	public TestServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@HystrixCommand(fallbackMethod="fallbackReadingList")
	public String readingList(){
		URI url = URI.create("http://localhost:8081/miicrown/test/hello/JackAndTom");
		
		return this.restTemplate.getForObject(url, String.class);
	}
	
	public String fallbackReadingList(){
		return "Error: Read List Fail!";
	}
	
}
