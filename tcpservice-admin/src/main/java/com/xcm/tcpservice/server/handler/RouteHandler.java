package com.xcm.tcpservice.server.handler;

import com.xcm.tcpservice.common.api.RouteApi;
import com.xcm.tcpservice.common.manager.ProxyManager;
import com.xcm.tcpservice.common.pojo.ClientStatusReqVO;
import com.xcm.tcpservice.common.pojo.TcpClientInfo;
import com.xcm.tcpservice.config.AppConfiguration;
import io.netty.channel.socket.nio.NioSocketChannel;
import okhttp3.OkHttpClient;
import okhttp3.Response;
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
    private final static Logger LOGGER = LoggerFactory.getLogger(RouteHandler.class);

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private AppConfiguration configuration;


    /**
     * 客户端上线
     *
     * @param clientId
     * @param channel
     * @throws IOException
     */
    public void clientOnLine(long clientId, NioSocketChannel channel) throws IOException {
        LOGGER.info("Account [{}] clientOnLine");
        //添加路由关系
        addRouteInfo(clientId);
        //保存客户端与 Channel 之间的关系
        SessionSocketHolder.put(clientId, channel);

    }


    /**
     * 用户下线
     *
     * @param clientId
     * @param channel
     * @throws IOException
     */
    public void clientOffLine(long clientId, NioSocketChannel channel) throws IOException {
        LOGGER.info("Account [{}] offline");
        //清除路由关系
        clearRouteInfo(clientId);
        SessionSocketHolder.remove(channel);
    }

    /**
     * 添加路由关系
     *
     * @param clientId
     * @throws IOException
     */
    public void addRouteInfo(long clientId) {
        RouteApi routeApi = new ProxyManager<>(RouteApi.class, configuration.getRouteUrl(), okHttpClient).getInstance();
        Response response = null;
        ClientStatusReqVO vo = new ClientStatusReqVO();
        vo.setClientId(clientId);
        try {
            response = (Response) routeApi.onLine(vo);
        } catch (Exception e){
            LOGGER.error("Exception",e);
        }finally {
            response.body().close();
        }
    }

    /**
     * 清除路由关系
     *
     * @param clientId
     * @throws IOException
     */
    public void clearRouteInfo(long clientId) {
        RouteApi routeApi = new ProxyManager<>(RouteApi.class, configuration.getRouteUrl(), okHttpClient).getInstance();
        Response response = null;
        ClientStatusReqVO vo = new ClientStatusReqVO();
        vo.setClientId(clientId);
        try {
            response = (Response) routeApi.offLine(vo);
        } catch (Exception e){
            LOGGER.error("Exception",e);
        }finally {
            response.body().close();
        }
    }
}
