package com.miicrown.kafka.producer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
//@EnableScheduling
//@EnableKafka
public class KafkaProducer {

	private Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate kafkaTemplate;
	
//	@Scheduled(cron=" 00/1 * * * * ?")
	public void sendTestMsg(){
		
		String message = UUID.randomUUID().toString();
		ListenableFuture future = kafkaTemplate.send("test", message);
		
		future.addCallback(succ -> {
			log.info("Test消息发送成功");
		}, fail -> {
			log.error("消息发送失败");
		});
		
	}
	
//	@Scheduled(cron=" 00/5 * * * * ?")
	public void sendChatMsg(){
		
		String message = UUID.randomUUID().toString();
		ListenableFuture future = kafkaTemplate.send("chat", message);
		
		future.addCallback(succ -> {
			log.info("Chat消息发送成功");
		}, fail -> {
			log.error("消息发送失败");
		});
		
	}
	
}
