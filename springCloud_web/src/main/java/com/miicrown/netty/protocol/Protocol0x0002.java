package com.miicrown.netty.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.miicrown.netty.service.SqlService;
import com.miicrown.util.ByteUtil;
import com.miicrown.util.CrcUtil;

import io.netty.buffer.ByteBuf;

public class Protocol0x0002 extends Protocol {
	
	
	public enum LampState{
		
		RUNNING(0x0000),   //设备正常
		FAULT(0x0001),    //设备故障
		;
		private int value;
		
		private LampState(int t){
			this.value = t;
		}
	
	}
	
	
	public Protocol0x0002(){
		super(ProtocolType.HEARTBEAT);
	}
	
	public Protocol0x0002(Protocol p) {
		super(ProtocolType.HEARTBEAT);
		
		// step 1
		this.setEquipId(p.getEquipId());
		this.setEquipType(p.getEquipType());
		this.setSerialNumber(SERIALNUMBER.incrementAndGet());
		// step 2
		this.setHeadAndContent(createHeadAndContent());
		this.setLength(this.getHeadAndContent().length);
		// step 3
		this.setVerification(ByteUtil.shortToByteArrayLE(CrcUtil.getCRC16(this.getHeadAndContent())));
		
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
			baos.write(LampState.RUNNING.value);
			
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
		String sql = "update t_lamp set "
				+ " heart_Beat_Date = now()"
				+ " where id = '%s' ";
		return String.format(sql, SqlService.map_lamps.get(getEquipId()) );
	}

}
