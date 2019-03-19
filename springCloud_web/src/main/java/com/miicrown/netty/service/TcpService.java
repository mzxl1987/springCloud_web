package com.miicrown.netty.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.miicrown.netty.server.Configuration;
import com.miicrown.netty.server.EchoServer;

@Service
public class TcpService {

	public EchoServer server;
	
	@PostConstruct
	public void InitTcpServer(){
		
		Configuration configuration = Configuration.createInstance();
    	configuration.setHost("127.0.0.1");
    	configuration.setPort(7005);
    	
    	this.server = new EchoServer(configuration);
    	this.server.start();
		
	}

	@PreDestroy
	public void destory(){
		this.server.stop();
	}
	
}
