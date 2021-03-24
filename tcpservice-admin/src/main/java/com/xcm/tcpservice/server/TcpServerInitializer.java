package com.xcm.tcpservice.server;

import com.xcm.tcpservice.common.service.RedisService;
import com.xcm.tcpservice.server.customdecoder.MyProtobufDecoder;
import com.xcm.tcpservice.server.customdecoder.MyProtobufEncoder;
import com.xcm.tcpservice.server.customdecoder.MyProtobufLengthEncoder;
import com.xcm.tcpservice.server.handler.RouteHandler;
import com.xcm.tcpservice.server.handler.TcpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Component
public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private RedisService redisService  ;
    @Autowired
    private RouteHandler routeHandler  ;


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ch.pipeline()
                //11 秒没有向客户端发送消息就发生心跳
                .addLast(new IdleStateHandler(30, 0, 0))
//                 google Protobuf 编解码
                //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
//                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new MyProtobufDecoder())
                //服务器端接收的是客户端CIMReqProtocol对象，所以这边将接收对象进行解码生产实列
//                .addLast(new ProtobufDecoder(CIMRequestProto.CIMReqProtocol.getDefaultInstance()))
                //解决Protocol的粘包、拆包问题
                //只需要在发送端加上编码器 ProtobufVarint32LengthFieldPrepender
                //接收方加上解码器 ProtobufVarint32FrameDecoder
                //Google Protocol Buffers编码器
//                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new MyProtobufLengthEncoder())
                //Google Protocol Buffers编码器
                .addLast(new MyProtobufEncoder())
                .addLast(new TcpServerHandler(redisService,routeHandler));
    }
}
