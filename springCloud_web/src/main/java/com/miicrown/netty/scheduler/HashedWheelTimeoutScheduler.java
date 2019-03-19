package com.miicrown.netty.scheduler;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.internal.PlatformDependent;

public class HashedWheelTimeoutScheduler implements CancelableScheduler {

	private final ConcurrentMap<SchedulerKey, Timeout> scheduledFutures = PlatformDependent.newConcurrentHashMap();
	private final HashedWheelTimer executorService;
	
	private volatile ChannelHandlerContext ctx;
	
	public HashedWheelTimeoutScheduler() {
		executorService = new HashedWheelTimer();
	}
	
	public HashedWheelTimeoutScheduler(ThreadFactory tf){
		executorService  = new HashedWheelTimer(tf);
	}
	
	@Override
	public void update(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void cancel(SchedulerKey key) {
		Timeout t = scheduledFutures.remove(key);
		if(t != null){
			t.cancel();
		}
	}

	@Override
	public void scheduleCallback(final SchedulerKey key, final Runnable runnable, long delay, TimeUnit unit) {
		Timeout t = executorService.newTimeout(new TimerTask() {
			@Override
			public void run(Timeout timeout) throws Exception {
				ctx.executor().execute(new Runnable() {
					public void run() {
						try{
							runnable.run();
						}finally{
							scheduledFutures.remove(key);
						}
					}
				});
				
			}
		}, delay, unit);
		
		replaceScheduledFuture(key, t);
		
	}

	@Override
	public void schedule(final Runnable runnable, long delay, TimeUnit unit) {
		executorService.newTimeout(new TimerTask() {
			@Override
			public void run(Timeout timeout) throws Exception {
				runnable.run();
			}
		}, delay, unit);
	}

	@Override
	public void schedule(final SchedulerKey key, final Runnable runnable, long delay, TimeUnit unit) {
		Timeout t = executorService.newTimeout(new TimerTask() {
			
			@Override
			public void run(Timeout timeout) throws Exception {
				try{
					runnable.run();
				}finally{
					scheduledFutures.remove(key);
				}
				
			}
		}, delay, unit);
		
		replaceScheduledFuture(key, t);
		
	}

	@Override
	public void shutdown() {
		executorService.stop();
	}

	private void replaceScheduledFuture(final SchedulerKey key, final Timeout t){
		final Timeout oldT;
		if(t.isExpired()){  oldT = scheduledFutures.remove(key);  }
		else{   oldT = scheduledFutures.put(key, t);   }
		
		if(null != oldT)  oldT.cancel();
		
	}
	
}
