package com.miicrown.websocket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration // 配置类  
@EnableWebSocket // 声明支持websocket
@ConditionalOnWebApplication
public class WebSocketConfigerImpl implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/ws").addInterceptors(myHandshake()).setAllowedOrigins("*");  
//        registry.addHandler(myHandler(), "/sockjs/ws").addInterceptors(myHandshake()).withSockJS();  
  
//        registry.addHandler(myHandler(), "/ws2").setAllowedOrigins("*");  
//        registry.addHandler(myHandler(), "/sockjs/ws2").setAllowedOrigins("*").withSockJS();  
		
	}
	
	 @Bean  
	public WebSocketHandlerImpl myHandler() {
		return new WebSocketHandlerImpl();
	}

	@Bean
	public HandshakeInterceptor myHandshake() {
		return new HandshakeInterceptor();
	}

	@Bean  
    public ServerEndpointExporter serverEndpointExporter() {  
        return new ServerEndpointExporter();  
    }  
	
	@Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        return taskScheduler;
    }
	
}
