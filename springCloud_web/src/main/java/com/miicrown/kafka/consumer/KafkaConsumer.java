package com.miicrown.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
//@EnableKafka
public class KafkaConsumer {
	
	private Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
//	@KafkaListener(topics="test")
	public void listenerTest(ConsumerRecord<?, ?> record){
		
		log.info("Kafka接收消息 : TOPIC [ TEST ]   :  MSG [ {} ]",record.value());
		
	}
	
//	@KafkaListener(topics="chat")
	public void listenerChat(ConsumerRecord<?, ?> record){
		
		log.info("Kafka接收消息 : TOPIC [ CHAT ]   :  MSG [ {} ]",record.value());
		
	}
	
}
