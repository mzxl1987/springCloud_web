package com.miicrown.netty.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

@Component
public class EchoServer {
	
	private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);
	
	public static ConcurrentHashMap<ChannelId, Channel> dic_channel = new ConcurrentHashMap<ChannelId, Channel>();
	
	private final Configuration configuration;
	private final Configuration configCopy;
	
	private ServerInitializer serverInitializer = new ServerInitializer();
	
	private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
	
	public EchoServer(Configuration configuration){
		this.configuration = configuration;
		this.configCopy = Configuration.cloneInstance(configuration);
	}
	
	public void start(){
		
		startAsync().syncUninterruptibly();
		
	}
	
	public Future<Void> startAsync(){
		
		initGroups();
			
		Class<? extends ServerChannel> channel = configCopy.isUseLinuxNativeEpoll() ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
		
		serverInitializer.start();
		
		ServerBootstrap b = new ServerBootstrap();  //TCP
		b.group(bossGroup, workerGroup)
		.channel(channel)
		.childHandler(serverInitializer);
		
		InetSocketAddress addr = new InetSocketAddress(configCopy.getPort());
		if(configCopy.getHost() != null){
			addr = new InetSocketAddress(configCopy.getHost(), configCopy.getPort());
		}
		
		b.localAddress(addr);
		
		return b.bind(addr).addListener(new FutureListener<Void>() {
			public void operationComplete(Future<Void> future) throws Exception {
				if(future.isSuccess()){
					logger.info("TCP Server 启动[成功], TCP://{}:{}",configCopy.getHost(),configCopy.getPort());
				}else{
					logger.error("TCP Server 启动[X失败X], TCP://{}:{}",configCopy.getHost(),configCopy.getPort());
				}
			}
		}) ;
		
	}
	
	public void stop(){
		
		bossGroup.shutdownGracefully().syncUninterruptibly();
		workerGroup.shutdownGracefully().syncUninterruptibly();
		
	}
	
	protected void initGroups() {
        
		if (configCopy.isUseLinuxNativeEpoll()) {
            bossGroup = new EpollEventLoopGroup(configCopy.getBossThreads());
            workerGroup = new EpollEventLoopGroup(configCopy.getWorkerThreads());
        } else {
            bossGroup = new NioEventLoopGroup(configCopy.getBossThreads());
            workerGroup = new NioEventLoopGroup(configCopy.getWorkerThreads());
        }
		
    }
	
	
}
