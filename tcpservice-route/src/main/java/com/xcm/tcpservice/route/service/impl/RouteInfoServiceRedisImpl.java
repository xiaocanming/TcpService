package com.xcm.tcpservice.route.service.impl;

import com.xcm.tcpservice.common.api.AdminApi;
import com.xcm.tcpservice.common.exception.TcpException;
import com.xcm.tcpservice.common.manager.ProxyManager;
import com.xcm.tcpservice.common.pojo.LoginReqVO;
import com.xcm.tcpservice.common.pojo.RouteInfo;
import com.xcm.tcpservice.common.pojo.SendMsgReqVO;
import com.xcm.tcpservice.common.pojo.TcpServerResVO;
import com.xcm.tcpservice.common.service.RedisService;
import com.xcm.tcpservice.common.util.RouteInfoParseUtil;
import com.xcm.tcpservice.route.service.ClientInfoCacheService;
import com.xcm.tcpservice.route.service.RouteInfoService;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.xcm.tcpservice.common.constant.DefaultConstants.LOGIN_STATUS_PREFIX;
import static com.xcm.tcpservice.common.constant.DefaultConstants.ROUTE_PREFIX;
import static com.xcm.tcpservice.common.enums.StatusEnum.OFF_LINE;


/**
 * Function:
 *
 * @author crossoverJie
 * Date: 2018/12/23 21:58
 * @since JDK 1.8
 */
@Service
public class RouteInfoServiceRedisImpl implements RouteInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RouteInfoServiceRedisImpl.class);

    @Autowired
    private RedisService redisService ;

    @Autowired
    private OkHttpClient okHttpClient;

    @Override
    public void saveRouteInfo(Long clientId, String msg) throws Exception {
        String key = ROUTE_PREFIX + clientId;
        redisService.set(key,msg);
    }

    @Override
    public void offLine(Long clientId) throws Exception {
        String key = ROUTE_PREFIX + clientId;
        //删除路由
        redisService.del(key);
        //删除登录状态
        redisService.sRemove(LOGIN_STATUS_PREFIX,clientId.toString());
    }

    @Override
    public void onLine(Long clientId) throws Exception {
        redisService.sAdd(LOGIN_STATUS_PREFIX,clientId.toString());
    }

    @Override
    public RouteInfo loadRouteRelatedByClientId(Long clientId) throws Exception{
        //判断是否存在路由
        Object value =   redisService.get(ROUTE_PREFIX + clientId);
        if (value == null) {
            throw new TcpException(OFF_LINE);
        }
        //判断是否在线
        if (!redisService.sIsMember(LOGIN_STATUS_PREFIX,clientId.toString())) {
            throw new TcpException(OFF_LINE);
        }
        return RouteInfoParseUtil.parse(value.toString());
    }

    @Override
    public void pushMsg(RouteInfo routeInfo, long receiveClientId, Object msgObject) throws Exception {
        String url = "http://" + routeInfo.getIp() + ":" + routeInfo.getHttpPort();
        AdminApi serverApi = new ProxyManager<>(AdminApi.class, url, okHttpClient).getInstance();
        SendMsgReqVO vo = new SendMsgReqVO(null,receiveClientId,msgObject);
        Response response = null;
        try {
            response = (Response) serverApi.sendMsg(vo);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
        } finally {
            response.body().close();
        }
    }
}
