package com.miicrown.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
	@Override  
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
        // TODO Auto-generated method stub  
        log.info("After handshake " + request.getRemoteAddress().toString());  
        super.afterHandshake(request, response, wsHandler, ex);  
    }  
  
    @Override  
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,  
            Map<String, Object> map) throws Exception {  
        // TODO Auto-generated method stub  
        log.info("Before handshake " + request.getRemoteAddress().toString());  
        return super.beforeHandshake(request, response, handler, map);  
    }  
}
