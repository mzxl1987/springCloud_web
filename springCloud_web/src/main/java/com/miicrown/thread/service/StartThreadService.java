package com.miicrown.thread.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.miicrown.thread.SimpleThread;

@Component
public class StartThreadService {
	
	@Autowired
	AsyncThreadService asyncTaskService;
	
	@Resource
	ThreadPoolTaskExecutor executor;
	
	/**
	 * 异步线程启动
	 */
	@PostConstruct
	public void init(){
		
		int i = 10;
		while(i > 0){
			try {
				asyncTaskService.executeMinus();
				asyncTaskService.executePlus();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			i--;
		}
	}
	
	/**
	 * 普通线程通过线程池启动
	 */
	@PostConstruct
	public void initOne(){
		
		int i = 10;
		while(i > 0){
			SimpleThread st = new SimpleThread();
			st.setIndex(i);
			executor.execute(st);
			i--;
		}
	}
	
}
