package com.xcm.tcpservice.common.kit;

import io.netty.channel.ChannelHandlerContext;

/**
 * @描述
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
