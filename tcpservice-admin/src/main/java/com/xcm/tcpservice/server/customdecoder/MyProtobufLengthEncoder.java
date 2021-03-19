package com.xcm.tcpservice.server.customdecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/8
 */
public class MyProtobufLengthEncoder extends MessageToByteEncoder<ByteBuf> {
    public MyProtobufLengthEncoder(){

    }
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int bodyLen = msg.readableBytes();
        int headerLen = 2;
        out.ensureWritable(headerLen + bodyLen);
        out.writeByte((byte) 0xEB);
        out.writeByte((byte) 0xEB);
        out.writeBytes(msg, msg.readerIndex(), bodyLen);
        out.writeByte((byte) 0x09);
    }
}
