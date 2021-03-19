package com.xcm.tcpservice.server.customdecoder;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/8
 */
public class MyProtobufEncoder extends MessageToMessageEncoder<Object> {
    public MyProtobufEncoder(){

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
        if (msg instanceof MessageLite) {
            out.add(Unpooled.wrappedBuffer(((MessageLite)msg).toByteArray()));
        }
        else if(msg instanceof String){
            out.add(Unpooled.wrappedBuffer(((String)msg).getBytes("utf-8")));
        }
        else {
            if (msg instanceof MessageLite.Builder) {
                out.add(Unpooled.wrappedBuffer(((MessageLite.Builder)msg).build().toByteArray()));
            }
        }
    }
}
