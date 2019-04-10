package com.miicrown.thread.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class AsyncThreadService {
	
	public static final AtomicInteger i = new AtomicInteger(0); 
	private Logger log = LoggerFactory.getLogger(AsyncThreadService.class);
	
	@Async
	public void executeMinus() throws InterruptedException{
		log.info("Async Minus");
		System.out.println(i.decrementAndGet());
	}
	
	@Async
	public void executePlus() throws InterruptedException{
		log.info("Async Plus");
		System.out.println(i.incrementAndGet());
	}
	
}
