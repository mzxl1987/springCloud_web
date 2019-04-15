package com.miicrown.rabbitMQ.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	private Logger log = LoggerFactory.getLogger(Consumer.class);
	
	@RabbitListener(queues="direct")
	public void listenerDirect(String msg){
		
		log.info(" 收到直达消息: {} ", msg);
		
	}
	
	/*********************************    TOPIC MESSAGE   ***********************************/
	
	@RabbitListener(queues="topic.msg")
	public void listenerMsg(String msg){
		log.info(" 收到 topic.msg : {} ", msg);
	}	
	
	@RabbitListener(queues="topic.messages")
	public void listenerMessage(String msg){
		log.info(" 收到 topic.messages : {} ", msg);
	}	
	
}
