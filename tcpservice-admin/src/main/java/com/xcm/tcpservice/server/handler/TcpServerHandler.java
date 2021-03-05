package com.xcm.tcpservice.server.handler;

import com.xcm.tcpservice.common.constant.DefaultConstants;
import com.xcm.tcpservice.common.exception.TcpException;
import com.xcm.tcpservice.common.kit.HeartBeatHandler;
import com.xcm.tcpservice.common.pojo.TcpClientInfo;
import com.xcm.tcpservice.common.protocol.CIMRequestProto;
import com.xcm.tcpservice.common.util.NettyAttrUtil;
import com.xcm.tcpservice.common.util.SpringBeanFactory;
import com.xcm.tcpservice.util.SessionSocketHolder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@ChannelHandler.Sharable
public class TcpServerHandler extends SimpleChannelInboundHandler<CIMRequestProto.CIMReqProtocol> {
    private final static Logger LOGGER = LoggerFactory.getLogger(TcpServerHandler.class);

    /**
     * 取消绑定
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //可能出现业务判断离线后再次触发 channelInactive
        TcpClientInfo userInfo = SessionSocketHolder.getUserId((NioSocketChannel) ctx.channel());
        if (userInfo != null){
            LOGGER.warn("[{}] trigger channelInactive offline!",userInfo.getClientName());

            //Clear route info and offline.
//            RouteHandler routeHandler = SpringBeanFactory.getBean(RouteHandler.class);
//            routeHandler.userOffLine(userInfo,(NioSocketChannel) ctx.channel());

            ctx.channel().close();
        }
    }

    /*
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connection");
        CIMRequestProto.CIMReqProtocol protocol = CIMRequestProto.CIMReqProtocol.newBuilder()
                .setRequestId(1111)
                .setReqMsg(ctx.channel().remoteAddress().toString())
                .setType(DefaultConstants.CommandType.MSG)
                .build();

        ChannelFuture future = ctx.writeAndFlush(protocol);
        future.addListener((ChannelFutureListener) channelFuture ->
                LOGGER.info("server push msg:[{}]", ctx.channel().remoteAddress().toString()));
        super.channelActive(ctx);
    }


//    //心跳过期检查
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
//            if (idleStateEvent.state() == IdleState.READER_IDLE) {
//
//                LOGGER.info("定时检测客户端端是否存活");
//
//                HeartBeatHandler heartBeatHandler = SpringBeanFactory.getBean(ServerHeartBeatHandlerImpl.class) ;
//                heartBeatHandler.process(ctx) ;
//            }
//        }
//        super.userEventTriggered(ctx, evt);
//    }

    //收到数据时调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CIMRequestProto.CIMReqProtocol msg) throws Exception {
        LOGGER.info("received msg=[{}]", msg.toString());

        if (msg.getType() == DefaultConstants.CommandType.LOGIN) {
            //保存客户端与 Channel 之间的关系
            SessionSocketHolder.put(msg.getRequestId(), (NioSocketChannel) ctx.channel());
            SessionSocketHolder.saveSession(msg.getRequestId(), msg.getReqMsg());
            LOGGER.info("client [{}] online success!!", msg.getReqMsg());
        }

        //心跳更新时间
        if (msg.getType() == DefaultConstants.CommandType.PING){
            NettyAttrUtil.updateReaderTime(ctx.channel(),System.currentTimeMillis());
            //向客户端响应 pong 消息
            CIMRequestProto.CIMReqProtocol heartBeat = SpringBeanFactory.getBean("heartBeat",
                    CIMRequestProto.CIMReqProtocol.class);
            ctx.writeAndFlush(heartBeat).addListeners((ChannelFutureListener) future -> {
                if (!future.isSuccess()) {
                    LOGGER.error("IO error,close Channel");
                    future.channel().close();
                }
            }) ;
        }

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (TcpException.isResetByPeer(cause.getMessage())) {
            return;
        }
        LOGGER.error(cause.getMessage(), cause);
    }
}
