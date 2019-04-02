package com.miicrown.thread.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

	private Logger log = LoggerFactory.getLogger(AsyncTaskService.class);
	
	@Async
	public void executeMinus() throws InterruptedException{
		log.info("Async Minus");
		Thread.sleep(1000);
	}
	
	@Async
	public void executePlus() throws InterruptedException{
		log.info("Async Plus");
		Thread.sleep(1000);
	}
	
}
