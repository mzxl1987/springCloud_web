package com.miicrown.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.miicrown.entity.User;
import com.miicrown.entity.dto.Result;
import com.miicrown.repository.UserRepository;
import com.miicrown.service.ApiService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional(rollbackOn=Exception.class)
public class ApiServiceImpl implements ApiService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object authLogin(User user, HttpServletRequest request){
		Result<?> r = Result.getInstance();
		
		List<User> l = userRepository.Query(user);
		if(null != l && !l.isEmpty()){
			List d = new ArrayList<>();
			String uuid = UuidUtil.getTimeBasedUuid().toString(); 
			d.add(uuid);
			request.getSession().setAttribute("token", uuid);
			r.setData(d);
		}else{
			r.setSuccess(false);
		}
		
		return r;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object permission(User user, HttpServletRequest request){
		
		String request_token = request.getParameter("token");
		String token = (String) request.getSession().getAttribute("token");
		
		log.info("request token:{} - session token:{}",request_token, token);
		
		Result<?> r = Result.getInstance();
		
		if(token == null || !token.equals(request_token)){
			r.setSuccess(false);
		}
		
		return r;
	}
	
	public Object auth2stepCode(User user){
		
		Result<?> r = Result.getInstance();
		
		return r;
	}
		
}
