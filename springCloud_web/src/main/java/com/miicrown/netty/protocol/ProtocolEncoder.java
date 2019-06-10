package com.miicrown.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ProtocolEncoder extends MessageToByteEncoder<Protocol> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Protocol pro, ByteBuf out) throws Exception {
		out.writeBytes(pro.toByteBuf());
//		out.writeByte(Protocol.HEAD);
//		out.writeShort(pro.getLength());
//		out.writeByte(Protocol.HEAD);
//		out.writeShort(pro.getType());
//		out.writeBytes(pro.getContent());
//		out.writeBytes(pro.getVerification());
//		out.writeByte(Protocol.TAIL);
	}

}
