package com.miicrown.netty.protocol;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ProtocolDecoder extends ByteToMessageDecoder {
	
	Logger log = LoggerFactory.getLogger(ProtocolDecoder.class);
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object> out) throws Exception {
		try{
			while(bb.isReadable()){
				Protocol pro = null;
				final int head_1 = bb.readUnsignedByte();
				if(head_1 == Protocol.HEAD && bb.readableBytes() >= Protocol.MINLENGTH){
					final int length = bb.readUnsignedShort();
					final int head_2 = bb.readUnsignedByte();
					if(head_2 == Protocol.HEAD){
						final int type = bb.readUnsignedShort();
						
						switch (type){
							case LoginProtocol.TYPE:                   pro = new LoginProtocol(type);            break;
							case ResponseProtocol.TYPE:                pro = new ResponseProtocol(type);         break;
						}
						
						if(null != pro){
							pro.setContent(bb,length);
							pro.setLength(length);
							pro.takeVerification(bb);
						}
					}
				}
				
				if(null != pro){
					out.add(pro);
				}
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}

}
