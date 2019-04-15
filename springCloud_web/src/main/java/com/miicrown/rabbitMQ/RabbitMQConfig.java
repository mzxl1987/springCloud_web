package com.miicrown.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	/*******************************  direction message  *******************************/
	/**
	 * Direction Message Queue
	 * 直达消息队列
	 * @return
	 */
	@Bean
	public Queue queueDirect(){
		return new Queue("direct");
	}
	
	/********************************  topic message  *******************************/
	
	@Bean(name="msg")
	public Queue queueMsg(){
		return new Queue("topic.msg");
	}
	
	@Bean(name="messages")
	public Queue queueMessages(){
		return new Queue("topic.messages");
	}
	
	@Bean
	public TopicExchange exchange(){
		return new TopicExchange("exchange");
	}
	
	@Bean
	Binding bindingExchangeMsg(@Qualifier("msg") Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with("topic.msg");
	}
	
	@Bean
	Binding bindingExchangeMessages(@Qualifier("messages") Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with("topic.messages");
	}
	
}
