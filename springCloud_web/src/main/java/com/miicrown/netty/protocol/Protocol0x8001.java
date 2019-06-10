package com.miicrown.netty.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.miicrown.util.ByteUtil;
import com.miicrown.util.CrcUtil;

public class Protocol0x8001 extends Protocol {
	
	public enum LoginResult{
	
		SUCCESS(0x0000),                         //成功
		LOGINED(0x0001),                         //已经登入
		NOT_EXIST(0x0002),                 //数据库中不存在该设备
		MSG_ERROR(0x0003)                       //消息错误
		;
		private int value;
		
		private LoginResult(int t){
			this.value = t;
		}
	
	}
	
	LoginResult result;
	
	public Protocol0x8001(Protocol p,LoginResult result) {
		super(ProtocolType.LOGIN_REPONSE);
		
		// step 1
		this.setEquipId(p.getEquipId());
		this.setEquipType(p.getEquipType());
		this.setSerialNumber(SERIALNUMBER.incrementAndGet());
		this.setResponseSerialNumber(p.getSerialNumber());
		this.result = result;
		// step 2
		this.setHeadAndContent(createHeadAndContent());
		this.setLength(this.getHeadAndContent().length);
		// step 3
		this.setVerification(ByteUtil.shortToByteArrayLE(CrcUtil.getCRC16(this.getHeadAndContent())));
	}

	@Override
	byte[] createHeadAndContent() {
		//拼装数据帧
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			
			baos.write(encodeHead());
			baos.write(ByteUtil.shortToByteArrayLE(this.getResponseSerialNumber()));
			baos.write(this.result.value);
			
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
