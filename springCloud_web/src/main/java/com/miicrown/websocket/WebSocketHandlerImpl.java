package com.miicrown.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketHandlerImpl implements WebSocketHandler {

	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
		log.info("Connection closed..." + arg0.getRemoteAddress().toString());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
		log.info("Connection established..." + arg0.getRemoteAddress().toString());  
	}

	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
		try {
			System.out.println("Req: " + arg1.getPayload());
			// 发送信息
			TextMessage returnMessage = new TextMessage(arg1.getPayload() + " received at server");
			arg0.sendMessage(returnMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		if (arg0.isOpen()) {
			arg0.close();
		}
		log.info(arg1.toString());
		log.info("WS connection error,close...");
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
