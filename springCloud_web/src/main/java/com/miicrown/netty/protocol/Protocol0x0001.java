package com.miicrown.netty.protocol;

import io.netty.buffer.ByteBuf;

public class Protocol0x0001 extends Protocol {
	
	Protocol0x0001() {
		super(ProtocolType.LOGIN);
	}
	
	@Override
	public boolean decode(ByteBuf bb, int len) {
		
		if(!decodeHead(bb)) return false;
		
		return true;
	}

	@Override
	byte[] createHeadAndContent() {
		return null;
	}

	@Override
	public
	String toInsertSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public
	String toUpdateSQL() {
		// TODO Auto-generated method stub
		return null;
	}

}
