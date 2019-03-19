package com.miicrown.netty.server;

/**
 * 配置内容
 * @author Administrator  朱露露
 *
 */
public class Configuration {

	private String host = "localhost";
	private int port = -1;
	
	private int timeout = 60;  //单位：秒
	
	private boolean useLinuxNativeEpoll;
	
	private int bossThreads = 0; // 0 = current_processors_amount * 2
    private int workerThreads = 0; // 0 = current_processors_amount * 2
	
    /**
     * 构造函数
     */
	private Configuration() {

	}
	
	/**
	 * 创建配置对象
	 * @return  返回Configuration对象
	 */
	public static Configuration createInstance(){
		return new Configuration();
	}
	
	/**
	 * 拷贝配置对象
	 * @param source     拷贝对象的原始值
	 * @return           返回考呗后的对象
	 */
	public static Configuration cloneInstance(Configuration source){
		
		Configuration dest = new Configuration();
		
		dest.setHost(source.getHost());
		dest.setPort(source.getPort());
		
		return dest;
		
	}

	/**************************    get/set    ****************************************/

	/**
	 * 
	 * @return  返回Host地址
	 */
	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}

	public boolean isUseLinuxNativeEpoll() {
		return useLinuxNativeEpoll;
	}

	public void setUseLinuxNativeEpoll(boolean useLinuxNativeEpoll) {
		this.useLinuxNativeEpoll = useLinuxNativeEpoll;
	}

	public int getBossThreads() {
		return bossThreads;
	}

	public void setBossThreads(int bossThreads) {
		this.bossThreads = bossThreads;
	}

	public int getWorkerThreads() {
		return workerThreads;
	}

	public void setWorkerThreads(int workerThreads) {
		this.workerThreads = workerThreads;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
