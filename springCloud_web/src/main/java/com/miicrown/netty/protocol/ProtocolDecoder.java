package com.miicrown.netty.protocol;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miicrown.util.CrcUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
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
				if(head_1 == Protocol.START && bb.readableBytes() >= Protocol.MINLENGTH){
					final int length = bb.readUnsignedShortLE();
					final int head_2 = bb.readUnsignedByte();
					if(head_2 == Protocol.START){
						
						bb.markReaderIndex();
						byte[] content = ByteBufUtil.getBytes(bb, bb.readerIndex(), length);
						bb.readerIndex(bb.readerIndex() + length);
						
						int crc = bb.readUnsignedShortLE();
						int end = bb.readByte();
						if(end == Protocol.END && CrcUtil.check(content, crc)){
							
							bb.resetReaderIndex();
							final int msgId = bb.readUnsignedShortLE();
							
							log.info("消息ID = {}",msgId);
							
							switch (ProtocolType.get(msgId)){
								case LOGIN:                                   pro = new Protocol0x0001();            break;
								case HEARTBEAT:                               pro = new Protocol0x0002();            break;
								default:                                                                break;
							}
							
							if(null != pro && pro.decode(bb,length)){
								out.add(pro);
							}
						}
					}
				}
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}

}
