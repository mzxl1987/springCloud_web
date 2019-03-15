package com.miicrown.service.impl;

import java.net.URI;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.client.RestTemplate;

import com.miicrown.entity.User;
import com.miicrown.repository.UserRepository;
import com.miicrown.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@Transactional
public class TestServiceImpl implements TestService{
	
	private static final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
	
	private final RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	public void saveUser(){
		
		User u = new User();
		u.setId("" + System.nanoTime());
		u.setUsername("HelloA");
		userRepository.save(u);
			
	}
		
}
