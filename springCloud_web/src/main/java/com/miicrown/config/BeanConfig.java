package com.miicrown.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

import com.miicrown.netty.server.EchoServer;

@Configuration
public class BeanConfig {
	
	@Bean
	public EchoServer echoServer(){
		
		com.miicrown.netty.server.Configuration configuration = com.miicrown.netty.server.Configuration.createInstance();
    	configuration.setHost("127.0.0.1");
    	configuration.setPort(7005);
		
		return new EchoServer(configuration);
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplateBuilder().build();
	}
	
	/**
	 * 解决WebSocket & Scheduler同时使用冲突的问题
	 * @return
	 */
	@Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        return taskScheduler;
    }
	
}
