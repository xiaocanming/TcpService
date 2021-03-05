package com.xcm.tcpservice.server.handler;

import com.xcm.tcpservice.common.pojo.TcpClientInfo;
import com.xcm.tcpservice.config.AppConfiguration;
import com.xcm.tcpservice.util.SessionSocketHolder;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Component
public class RouteHandler {
//    private final static Logger LOGGER = LoggerFactory.getLogger(RouteHandler.class);
//
//    @Autowired
//    private OkHttpClient okHttpClient;
//
//    @Autowired
//    private AppConfiguration configuration;
//
//    /**
//     * 用户下线
//     *
//     * @param tcpClientInfo
//     * @param channel
//     * @throws IOException
//     */
//    public void userOffLine(TcpClientInfo tcpClientInfo, NioSocketChannel channel) throws IOException {
//        if (tcpClientInfo != null) {
//            LOGGER.info("Account [{}] offline", tcpClientInfo.getClientName());
//            SessionSocketHolder.removeSession(tcpClientInfo.getClientId());
//            //清除路由关系
//            clearRouteInfo(tcpClientInfo);
//        }
//        SessionSocketHolder.remove(channel);
//
//    }
//
//
//    /**
//     * 清除路由关系
//     *
//     * @param userInfo
//     * @throws IOException
//     */
//    public void clearRouteInfo(TcpClientInfo userInfo) {
//        RouteApi routeApi = new ProxyManager<>(RouteApi.class, configuration.getRouteUrl(), okHttpClient).getInstance();
//        Response response = null;
//        ChatReqVO vo = new ChatReqVO(userInfo.getUserId(), userInfo.getUserName());
//        try {
//            response = (Response) routeApi.offLine(vo);
//        } catch (Exception e){
//            LOGGER.error("Exception",e);
//        }finally {
//            response.body().close();
//        }
//    }
}
