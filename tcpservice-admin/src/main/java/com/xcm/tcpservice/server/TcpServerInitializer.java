package com.xcm.tcpservice.server;

import com.xcm.tcpservice.common.protocol.CIMRequestProto;
import com.xcm.tcpservice.server.handler.TcpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {
    private final TcpServerHandler tcpServerHandle = new TcpServerHandler() ;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ch.pipeline()
                //11 秒没有向客户端发送消息就发生心跳
//                .addLast(new IdleStateHandler(11, 0, 0))
                // google Protobuf 编解码
                //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
                .addLast(new ProtobufVarint32FrameDecoder())
                //服务器端接收的是客户端CIMReqProtocol对象，所以这边将接收对象进行解码生产实列
                .addLast(new ProtobufDecoder(CIMRequestProto.CIMReqProtocol.getDefaultInstance()))
                //Google Protocol Buffers编码器
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                //Google Protocol Buffers编码器
                .addLast(new ProtobufEncoder())
                .addLast(tcpServerHandle);
    }
}
