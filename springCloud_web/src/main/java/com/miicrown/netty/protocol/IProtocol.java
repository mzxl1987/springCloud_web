package com.miicrown.netty.protocol;

import io.netty.buffer.ByteBuf;

public interface IProtocol {
	
	/**
	 * 将ByteBuf中的byte数据解析成对象中的属性
	 * @param bb
	 * @param len
	 * @return
	 */
	boolean decode(ByteBuf bb,int len);                 //解析数据
	/**
	 * 将ByteBuf中的byte数据解析成相应的帧头属性，通用
	 * @param bb
	 * @return
	 */
	boolean decodeHead(ByteBuf bb);                    //解析帧头
	byte[] encodeHead();                              //拼装帧头
	
	
	ByteBuf toByteBuf();
}
