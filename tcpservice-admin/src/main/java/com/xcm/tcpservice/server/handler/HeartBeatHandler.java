package com.xcm.tcpservice.server.handler;

import io.netty.channel.ChannelHandlerContext;

/**
 * @描述 心跳Handler
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
public interface HeartBeatHandler {
    /**
     * 处理心跳
     * @param ctx
     * @throws Exception
     */
    void process(ChannelHandlerContext ctx) throws Exception ;
}
