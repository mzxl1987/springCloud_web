package com.miicrown.netty.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Protocol0xB106 extends Protocol {
	
	private int controlType;
	
	public Protocol0xB106() {
		super(ProtocolType.SET_CONTROL_MODE);
	}
	
	@Override
	public boolean decode(ByteBuf bb, int len) {
		
		if(!decodeHead(bb)) return false;
		
		return true;
	}

	@Override
	byte[] createHeadAndContent() {
		//拼装数据帧
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			
			baos.write(encodeHead());
			baos.write(this.getControlType());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return baos.toByteArray();
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
