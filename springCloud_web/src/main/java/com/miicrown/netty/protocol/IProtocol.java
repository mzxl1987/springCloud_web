package com.miicrown.netty.protocol;

import io.netty.buffer.ByteBuf;

public interface IProtocol {
	
	void decode();                      //解析数据
	byte[] encode();                   //拼装数据
	
	ByteBuf toByteBuf();
}
