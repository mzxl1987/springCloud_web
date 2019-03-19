package com.miicrown.netty.server;

import com.miicrown.netty.protocol.ProtocolDecoder;
import com.miicrown.netty.protocol.ProtocolEncoder;
import com.miicrown.netty.scheduler.CancelableScheduler;
import com.miicrown.netty.scheduler.HashedWheelTimeoutScheduler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class ServerInitializer extends ChannelInitializer<Channel> {
	
	private CancelableScheduler scheduler = new HashedWheelTimeoutScheduler();
	
	private EchoServerHandler echoServerHandler;
	
	public void start(){
		echoServerHandler = new EchoServerHandler(scheduler);
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		
		ch.pipeline()
		.addLast(new ProtocolDecoder())
		.addLast(new ProtocolEncoder())
		.addLast(echoServerHandler);

	}

}
