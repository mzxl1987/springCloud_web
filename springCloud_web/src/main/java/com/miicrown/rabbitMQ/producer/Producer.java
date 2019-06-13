package com.miicrown.rabbitMQ.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Producer {
	
	private Logger log = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	AmqpTemplate amqpTemplate;

	@Scheduled(fixedDelay = 1000L)
	public void SendDirect(){
		amqpTemplate.convertAndSend("direct","Direct Message");
		log.info(" AMQP 发送 Direction 消息  ");
	}	
	
	/**
	 * 定时发送消息
	 */
	/****************************************     TOPIC MESSAGE    ***************************************/
	@Scheduled(fixedDelay = 1000L)
	public void SendMsg(){
		amqpTemplate.convertAndSend("exchange", "topic.msg", "MSG");
	}
	
	@Scheduled(fixedDelay = 1000L)
	public void SendMessages(){
		amqpTemplate.convertAndSend("exchange", "topic.messages", "MESSAGES");
	}
	
}
