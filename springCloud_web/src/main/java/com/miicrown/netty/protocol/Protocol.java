package com.miicrown.netty.protocol;

import com.miicrown.netty.verification.IVerification;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

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
public class Protocol implements IProtocol, IVerification{
	
	public static final byte HEAD = 0x68;
	public static final byte TAIL = 0x16;
	public static final int MINLENGTH = 7;
	
	private int length;
	private int type;
	private byte[] content;
	private byte[] verification;
	
	Protocol(int type) {
		this.type = type;
	}
	
	@Override
	public ByteBuf toByteBuf() {
		return Unpooled.copiedBuffer(
				 new byte[]{(byte) Protocol.HEAD}
				,new byte[]{(byte) ((length >> 8) & 0xFF),(byte) (length & 0xFF)}
				,new byte[]{(byte) Protocol.HEAD}
				,new byte[]{(byte) ((type >> 8) & 0xFF),(byte) (type & 0xFF)}
				,content
				,verification
				,new byte[]{(byte) Protocol.TAIL}
		);
	}

	@Override
	public byte[] encodeVerification(byte[] data) {
		
		if(null != data && data.length > 0){
			this.verification = new byte[]{ 0x00, 0x00 };
		}else{
			this.verification = new byte[]{ 0x00, 0x00 };
		}
		
		return this.verification;
	}

	@Override
	public boolean compare(byte[] data, byte[] dest) {
		byte[] result = encodeVerification(data); 
		if(null != result && result.length > 0 && null != dest && dest.length > 0){
			
			for(int i = 0, c = result.length; i < c; i++){
				if(dest[i] != result[i])    return false;
			}
			
			return true;
		}
		
		return false;
	}
 
	@Override
	public void takeVerification(ByteBuf bb) {
		final int l = 2;
		ByteBuf tmp_bb = bb.readSlice(l);
		this.verification = new byte[l];
		tmp_bb.readBytes(this.verification);
	}

	@Override
	public void decode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] encode() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}


	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}


	/**
	 * @return the head
	 */
	public static byte getHead() {
		return HEAD;
	}


	/**
	 * @return the tail
	 */
	public static byte getTail() {
		return TAIL;
	}


	/**
	 * @return the minlength
	 */
	public static int getMinlength() {
		return MINLENGTH;
	}


	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	
	

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}


	/**
	 * 设置协议内容
	 * @param bb    ByteBuf参数,存有byte数据
	 * @param len   需要截取内容的长度
	 */
	public void setContent(ByteBuf bb,int len) {
		ByteBuf t_bb = bb.readSlice(len);
		this.content = new byte[len];
		t_bb.readBytes(this.content);
	}
	
	public void setContent(byte[] c) {
		this.content = c;
	}


	/**
	 * @return the verification
	 */
	public byte[] getVerification() {
		return verification;
	}


	/**
	 * @param verification the verification to set
	 */
	public void setVerification(byte[] verification) {
		this.verification = verification;
	}

	
	

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Protocol [length=" + length + ", type=" + type + ", content=" + content + ", verification="
				+ verification + "]";
	}

	

	

	
	
}
