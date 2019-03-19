package com.miicrown.netty.protocol;

public class LoginProtocol extends Protocol{
	
	public static final int TYPE = 0x1000;
	
	public LoginProtocol(int type) {
		super(type);
	}
	
}
