package com.miicrown.netty.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miicrown.netty.server.EchoServer;

@Service
public class TcpService {

	@Autowired
	public EchoServer echoServer;
	
	@PostConstruct
	public void InitTcpServer(){
    	this.echoServer.start();
	}

	@PreDestroy
	public void destory(){
		this.echoServer.stop();
	}
	
}
