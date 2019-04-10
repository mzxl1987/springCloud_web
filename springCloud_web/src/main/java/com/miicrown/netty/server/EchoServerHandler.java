package com.miicrown.netty.server;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.miicrown.config.ApplicationContextProvider;
import com.miicrown.netty.protocol.LoginProtocol;
import com.miicrown.netty.protocol.Protocol;
import com.miicrown.netty.protocol.ResponseProtocol;
import com.miicrown.netty.scheduler.CancelableScheduler;
import com.miicrown.netty.scheduler.HashedWheelTimeoutScheduler;
import com.miicrown.netty.scheduler.SchedulerKey;
import com.miicrown.service.TestService;
import com.miicrown.service.impl.TestServiceImpl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.PlatformDependent;

@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(EchoServerHandler.class);
	private static final int HEARTBEAT_TIMEOUT = 60;  //心跳超时时间 60s
	private static final Object MAP_VALUE = new Object();
	public static final Map<Channel, Object> clients = PlatformDependent.newConcurrentHashMap();
	public static final CancelableScheduler disconnectScheduler = new HashedWheelTimeoutScheduler();
	public static final BlockingQueue<String> a = new LinkedBlockingQueue<>();
	
	public TestService testService;
	
	public EchoServerHandler() {
		this.testService = ApplicationContextProvider.getBean(TestService.class);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}
	
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		
		clients.put(ctx.channel(), EchoServerHandler.MAP_VALUE);
		
		log.info(" 新的连接 {} , 客户端数量 ：{}",ctx, clients.size());
		
		disconnectScheduler.schedule(new SchedulerKey(SchedulerKey.Type.HEARTBEAT_TIMEOUT, ctx), new Runnable() {
			@Override
			public void run() {
				clients.remove(ctx.channel());
				ctx.channel().close();
				log.info("关闭客户端 {}, 客户端数量 {}", ctx,clients.size());
			}
		}, HEARTBEAT_TIMEOUT, TimeUnit.SECONDS);
		
		super.channelActive(ctx);
		
	}
	
	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
		
		if(msg instanceof LoginProtocol){
			
			log.info( "{} , Message : {} ",ctx.channel().id().asLongText(),msg);
			
			testService.saveUser();
			
			Protocol response = null;
			
			response = new ResponseProtocol(ResponseProtocol.TYPE);
			response.setLength(3);
			response.setContent(new byte[]{ (byte)0x88 ,(byte)0x89, (byte)0x90 });
			response.encodeVerification(response.getContent());
			
			disconnectScheduler.schedule(new SchedulerKey(SchedulerKey.Type.HEARTBEAT_TIMEOUT, ctx), new Runnable() {
				@Override
				public void run() {
					clients.remove(ctx.channel());
					ctx.channel().close();
					log.info("关闭客户端 {}, 客户端数量 {}", ctx,clients.size());
				}
			}, HEARTBEAT_TIMEOUT, TimeUnit.SECONDS);
			
			if(null != response){
				ctx.writeAndFlush(response);
			}
			
		}else{
			ctx.fireChannelRead(msg);
		}	
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//		.addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		log.error("cause Exception!");
		EchoServer.dic_channel.remove(ctx.channel().id());
		log.error("Dic size:" + EchoServer.dic_channel.size());
		ctx.close();
	}
	
}
