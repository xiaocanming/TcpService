package com.xcm.tcpservice.server.customdecoder;

import com.xcm.tcpservice.common.protocol.CIMRequestProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/8
 */
public class MyProtobufDecoder extends ByteToMessageDecoder {
    public  MyProtobufDecoder(){

    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        //标记当前位置
        in.markReaderIndex();
        while (in.isReadable()){
            byte tmp1 = in.readByte();
            if(tmp1==(byte) 0xEB){
                if(in.isReadable()){
                    byte tmp2 = in.readByte();
                    if(tmp2==(byte) 0xEB){
                        int index=in.readerIndex();
//                        if(in.getByte(index+25)==(byte) 0x09){
//
//                            // 字节转成对象
//                            ByteBuf frame = Unpooled.buffer(25);
//                            in.readBytes(frame);
//                            byte[] inByte = frame.array();
//                            CIMRequestProto.CIMReqProtocol msg=CIMRequestProto.CIMReqProtocol.parseFrom(inByte);
//                            if (msg != null) {
//                                // 获取业务消息头
//                                out.add(msg);
//                            }
//                            byte tmp3 = in.readByte();
//                            return;
//                        }

                        if(in.getByte(index+24)==(byte) 0x09){

                            // 字节转成对象
                            ByteBuf frame = Unpooled.buffer(24);
                            in.readBytes(frame);
                            byte[] inByte = frame.array();
//                            CIMRequestProto.CIMReqProtocol msg=CIMRequestProto.CIMReqProtocol.parseFrom(inByte);
//                            if (msg != null) {
//                                // 获取业务消息头
//                                out.add(msg);
//                            }
                            String msg=new String(inByte,"utf-8");
                            out.add(msg);
                            byte tmp3 = in.readByte();
                            return;
                        }
                    }
                }
            }
        }
        //返回标记位置
        in.resetReaderIndex();
    }
}
