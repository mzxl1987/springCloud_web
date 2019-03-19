package com.miicrown.netty.protocol;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

public class ProtocalMsg {

	public static AtomicInteger ai = new AtomicInteger(0);
	
	public static final int HEAD = 0xDDDD;
	public static final int TAIL = 0xBBBB;
	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int head;
	private int type;
	private int length;
	private byte[] data;
	private int tail;
	
	private ProtocalMsg(){
		
	}
	
	public int finish(){
		return ai.incrementAndGet();
	}
	
	
	
	public int getType() {
		return type;
	}

	public ProtocalMsg setType(int type) {
		this.type = type;
		return this;
	}

	public int getHead() {
		return head;
	}
	public ProtocalMsg setHead(int head) {
		this.head = head;
		return this;
	}
	public int getLength() {
		return length;
	}
	public ProtocalMsg setLength(int length) {
		this.length = length;
		return this;
	}
	public byte[] getData() {
		return data;
	}
	public ProtocalMsg setData(byte[] data) {
		this.data = data;
		return this;
	}
	public int getTail() {
		return tail;
	}
	public ProtocalMsg setTail(int tail) {
		this.tail = tail;
		return this;
	}
	
	public static ProtocalMsg createInstance(){
		ProtocalMsg pm = new ProtocalMsg()
				.setHead(HEAD)
				.setTail(TAIL);
		return pm;
	}
	
	public ByteBuf toByteBuf(){
		return Unpooled.copiedBuffer(
				new byte[]{(byte) ((head >> 8) & 0xFF),(byte) (head & 0xFF)}
				,new byte[]{(byte) type}
				,new byte[]{(byte) ((length >> 8) & 0xFF),(byte) (length & 0xFF)}
				,data
				,new byte[]{(byte) ((tail >> 8) & 0xFF),(byte) (tail & 0xFF)}
		);
	}

	@Override
	public String toString() {
		return "ProtocalMsg [head=" + head + ", type=" + type + ", length=" + length + ", data=" + Arrays.toString(data)
				+ ", tail=" + tail + "]";
	}
	
	
	
}
