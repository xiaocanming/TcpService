package com.xcm.tcpservice.server;

import com.xcm.tcpservice.common.constant.DefaultConstants;
import com.xcm.tcpservice.common.pojo.SendMsgReqVO;
import com.xcm.tcpservice.common.protocol.CIMRequestProto;
import com.xcm.tcpservice.server.handler.SessionSocketHolder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @描述 tcp服务启动类
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Component
public class TcpServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(TcpServer.class);

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    @Value("${tcp.server.port}")
    private int nettyPort;

    @Autowired
    private TcpServerInitializer tcpServerInitializer;

    /**
     * 启动 cim server
     * @return
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(tcpServerInitializer);

        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            LOGGER.info("Start cim server success!!!");
        }
    }


    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        LOGGER.info("Close cim server success!!!");
    }


    /**
     * 发送消息到客户端
     * @param sendMsgReqVO 消息
     */
    public void sendMsg(SendMsgReqVO sendMsgReqVO){
        NioSocketChannel socketChannel = SessionSocketHolder.get(sendMsgReqVO.getReceiveClientId());

        if (null == socketChannel) {
            LOGGER.error("client {} offline!", sendMsgReqVO.getReceiveClientId());
            return;
        }
        CIMRequestProto.CIMReqProtocol protocol = CIMRequestProto.CIMReqProtocol.newBuilder()
                .setRequestId(sendMsgReqVO.getReceiveClientId())
                .setReqMsg(sendMsgReqVO.getMsg().toString())
                .setType(DefaultConstants.CommandType.MSG)
                .build();

        ChannelFuture future = socketChannel.writeAndFlush(protocol);
        future.addListener((ChannelFutureListener) channelFuture ->
                LOGGER.info("server push msg:[{}]", sendMsgReqVO.toString()));
    }
}
