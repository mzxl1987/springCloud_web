package com.miicrown.netty.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.bouncycastle.util.Arrays;

import com.miicrown.util.ByteUtil;
import com.miicrown.util.DateUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.ToString;

/**
 * 协议框架： 消息头 + 消息类型 + 消息体 + crc校验 + 消息尾 
 * 消息头的格式为 : 0x68 0x00 0x01 0x68 , 0x68为通用格式, 0x0001为消息体的长度       4bytes
 * 消息类型：用户自定义消息类型                           2byte
 * 消息体：用户自定义内容，                                   不定长度                   
 * crc校验：[消息体] 的CRC校验,             不定长度
 * 帧尾：0x16                              1byte 
 * @author Administrator    朱露露
 *
 */

@Data
@ToString
public abstract class Protocol implements IProtocol {
	
	public static final AtomicInteger SERIALNUMBER = new AtomicInteger(0);
	
	public static final byte START = 0x68;
	public static final byte END = 0x16;
	public static final int MINLENGTH = 7;
	public static final int FLAG_SUB_PACKAGE = (1 << 14);
	public static final int FLAG_TIME_TAG = (1 << 13);
	public static final int FLAG_RSA = (1 << 12);
	public static final int EQUIP_TYPE = 0x02;
	
	private int length;
	private ProtocolType type;
	
	/************ HEAD *****************/
	private int msgId;          //消息ID
	private int msgProperty;    //消息体属性
	private int equipType;      //设备类型
	private String equipId;        //终端ID
	private int serialNumber;           //消息流水号
	private int responseSerialNumber;   //需要回应的消息流水号
	private int totalPkg;        //消息包总数
	private int indexPkg;        //当前消息包下标,从1开始
	private String datetime;       //时间标签
	/***********************************/
	
	private byte[] headAndContent;
	private byte[] verification;
	
	Protocol(ProtocolType type) {
		this.type = type;
		this.msgId = this.type.getType();
	}
	
	@Override
	public ByteBuf toByteBuf() {
		
		return Unpooled.copiedBuffer(
				 new byte[]{(byte) Protocol.START}
				,ByteUtil.shortToByteArrayLE(this.length)
				,new byte[]{(byte) Protocol.START}
				,headAndContent
				,verification
				,new byte[]{(byte) Protocol.END}
		);
	}

	/* (non-Javadoc)
	 * @see com.miicrown.netty.protocol.IProtocol#decode(io.netty.buffer.ByteBuf, int)
	 */
	@Override
	public boolean decode(ByteBuf bb, int len) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.miicrown.netty.protocol.IProtocol#decodeHead(io.netty.buffer.ByteBuf)
	 */
	@Override
	public boolean decodeHead(ByteBuf bb) {
		byte[] tmp = new byte[6];
		byte[] tmp_equip = new byte[5];
		
		//读取消息体属性
		msgProperty = bb.readUnsignedShortLE();
		
		//设备类型
		equipType = bb.readByte();
		//读取设备编号
		bb.readBytes(tmp_equip);
		equipId = ByteUtil.bcdBytesToString(Arrays.reverse(tmp_equip));
		
		//读取流水号
		serialNumber = bb.readUnsignedShortLE();
		
		//读取消息分包項
		if((msgProperty & FLAG_SUB_PACKAGE) == FLAG_SUB_PACKAGE){
			totalPkg = bb.readUnsignedShortLE();
			indexPkg = bb.readUnsignedShortLE();
		}
		
		//读取时间
		if((msgProperty & FLAG_TIME_TAG) == FLAG_TIME_TAG){
			bb.readBytes(tmp);
			datetime = ByteUtil.bcdBytesToString(tmp);
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.miicrown.netty.protocol.IProtocol#encodeHead()
	 */
	@Override
	public byte[] encodeHead() {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		msgProperty |= FLAG_TIME_TAG;
		
		try {
			baos.write(ByteUtil.shortToByteArrayLE(msgId));
			baos.write(ByteUtil.shortToByteArrayLE(msgProperty));
			baos.write(equipType);
			baos.write(Arrays.reverse(ByteUtil.stringToBCDBytes(equipId)));
			baos.write(ByteUtil.shortToByteArrayLE(serialNumber));
			
			if((msgProperty & FLAG_SUB_PACKAGE) == FLAG_SUB_PACKAGE){
				baos.write(ByteUtil.shortToByteArrayLE(totalPkg));
				baos.write(ByteUtil.shortToByteArrayLE(indexPkg));
			}
			
			baos.write(ByteUtil.stringToBCDBytes(DateUtil.nowToYYMMddHHmmss()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return baos.toByteArray();
	}


	abstract byte[] createHeadAndContent();
	public abstract String toInsertSQL();
	public abstract String toUpdateSQL();
	
 	
}


