package com.miicrown.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miicrown.entity.User;
import com.miicrown.entity.dto.UserDto;
import com.miicrown.repository.UserRepository;
import com.miicrown.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@Transactional(rollbackOn=Exception.class)
public class TestServiceImpl implements TestService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
		
	public void saveUser() throws Exception{
		User us = new User();
		us.setId("" + System.nanoTime());
//		us.setUsername("HelloA");
		us.setUsername("HelloA_" + System.nanoTime());
		
		//userRepository.save(us);
		jdbcTemplate.execute("update t_user t set t.username = 'a'");
		
	}
		
	public List<UserDto> findUsers(String username) throws Exception {
		List<User> users = userRepository.findAllByName(username);
		List<UserDto> dtos = new ArrayList<>();
		
		users.stream().forEach(user -> {
			UserDto ud = new UserDto();
			BeanUtils.copyProperties(user, ud);
			dtos.add(ud);
		});
		
		return dtos;
	}
		
}
