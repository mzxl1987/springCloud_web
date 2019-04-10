package com.miicrown.schedule;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableScheduling
public class SimpleSchedule {

	public static final AtomicInteger i = new AtomicInteger(0);
	private Logger log = LoggerFactory.getLogger(SimpleSchedule.class);
	
	/**
	 * 定时任务
	 */
	@Scheduled(cron="0/10 * * * * ?")
	public void scheduleOne(){
		
		log.info("schedule 1, 运行次数: {} ",i.incrementAndGet());
		
	}
	
}
