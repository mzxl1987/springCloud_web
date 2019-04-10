package com.miicrown.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleThread implements Runnable {
	
	public Logger log = LoggerFactory.getLogger(SimpleThread.class);
	
	private int index;
	
	public void setIndex(int index){
		this.index = index;
	}
	
	@Override
	public void run() {
		
		log.info(" thread index = {} ",index);

	}

}
