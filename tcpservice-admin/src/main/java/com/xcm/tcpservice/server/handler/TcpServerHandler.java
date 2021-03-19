package com.xcm.tcpservice.server.handler;

import com.xcm.tcpservice.common.constant.DefaultConstants;
import com.xcm.tcpservice.common.exception.TcpException;
import com.xcm.tcpservice.common.pojo.TcpClientInfo;
import com.xcm.tcpservice.common.protocol.CIMRequestProto;
import com.xcm.tcpservice.common.util.NettyAttrUtil;
import com.xcm.tcpservice.common.util.SpringBeanFactory;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @描述 tcp服务Handler
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@ChannelHandler.Sharable
public class TcpServerHandler extends ChannelInboundHandlerAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(TcpServerHandler.class);

    @Autowired
    private  RouteHandler routeHandler;
    /**
     * 取消绑定
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //可能出现业务判断离线后再次触发 channelInactive
        long clientId = SessionSocketHolder.getClientId((NioSocketChannel) ctx.channel());
        if(clientId!=0L){
            LOGGER.info("[{}] connection!",clientId);
            routeHandler.clientOffLine(clientId,(NioSocketChannel) ctx.channel());
        }
        ctx.channel().close();
    }

    /*
     * 建立连接时，客户端上线
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        long clientId = SessionSocketHolder.getClientId((NioSocketChannel) ctx.channel());
        if(clientId!=0L){
            LOGGER.info("[{}] connection!",clientId);
            routeHandler.clientOnLine(clientId,(NioSocketChannel) ctx.channel());
        }
        super.channelActive(ctx);
    }

    //心跳过期检查
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {

                LOGGER.info("定时检测客户端端是否存活");

                HeartBeatHandler heartBeatHandler = SpringBeanFactory.getBean(ServerHeartBeatHandlerImpl.class) ;
                heartBeatHandler.process(ctx) ;
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    //收到数据时调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("received:" + msg);
        try {
            // 如果是protobuf类型的数据
            if (msg instanceof CIMRequestProto.CIMReqProtocol) {
                CIMRequestProto.CIMReqProtocol msgcim = (CIMRequestProto.CIMReqProtocol) msg;
                msgcim.getReqMsg();
                //心跳更新时间
                if (msgcim.getType() == DefaultConstants.CommandType.PING){
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
            else if(msg instanceof String){
                LOGGER.info("解析成功 msg=[{}]", msg.toString());
            }
            else {
                LOGGER.info("未知数据!" + msg);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (TcpException.isResetByPeer(cause.getMessage())) {
            return;
        }
        LOGGER.error(cause.getMessage(), cause);
        ctx.close();
    }
}
