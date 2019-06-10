package com.miicrown.netty.protocol;

public class LoginProtocol extends Protocol{
	
	public LoginProtocol(int type) {
		super(ProtocolType.LOGIN);
	}

	@Override
	byte[] createHeadAndContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toInsertSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toUpdateSQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
