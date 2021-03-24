package com.xcm.tcpservice.server.handler;

import com.xcm.tcpservice.common.pojo.TcpClientInfo;
import com.xcm.tcpservice.common.util.NettyAttrUtil;
import com.xcm.tcpservice.config.AppConfiguration;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述 心跳进程实现类
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Service
public class ServerHeartBeatHandlerImpl implements HeartBeatHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerHeartBeatHandlerImpl.class);

    @Autowired
    private RouteHandler routeHandler ;

    @Autowired
    private AppConfiguration appConfiguration ;

    @Override
    public void process(ChannelHandlerContext ctx) throws Exception {

        long heartBeatTime = appConfiguration.getHeartBeatTime() * 1000;

        Long lastReadTime = NettyAttrUtil.getReaderTime(ctx.channel());
        long now = System.currentTimeMillis();
        if (lastReadTime != null && now - lastReadTime > heartBeatTime){
            long clientId = SessionSocketHolder.getClientId((NioSocketChannel) ctx.channel());
            if(clientId!=0L){
                LOGGER.warn("客户端[{}]心跳超时[{}]ms，需要关闭连接!",clientId,now - lastReadTime);
            }
            routeHandler.clientOffLine(clientId, (NioSocketChannel) ctx.channel());
            ctx.channel().close();
        }
    }
}
