package com.miicrown.netty.verification;

import io.netty.buffer.ByteBuf;

public interface IVerification {
	
	/**
	 * 截取验证内容
	 * @param bb   存放验证内容的ByteBuf变量
	 */
	void takeVerification(ByteBuf bb);
	
	/**
	 * 生成新的校验码
	 * @param data  需要校验的内容
	 * @return      返回根据内容生成的校验码
	 */
	byte[] encodeVerification(byte[] data);
	
	/**
	 * 比较校验值
	 * @param data      协议原始内容值
	 * @param dest      协议中的校验值
	 * @return          根据原始内容生成校验值A,与协议中的校验值B的比较结果, return A == B;
	 */
	boolean compare(byte[] data, byte[] dest);

}
