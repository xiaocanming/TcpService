package com.xcm.tcpservice.route.service;

import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.pojo.*;

import java.util.Set;


/**
 * @描述 路由服务
 * @创建人 xcm
 * @创建时间 2021/3/8
 */
public interface RouteInfoService {

    /**
     * 保存路由信息
     * @param msg 服务器信息
     * @param clientId 用户信息
     * @throws Exception
     */
    void saveRouteInfo(Long clientId, String msg) throws Exception ;

    /**
     * 用户下线
     * @param clientId 下线用户ID
     * @throws Exception
     */
    void offLine(Long clientId) throws Exception;

    /**
     * 用户下线
     * @param clientId 下线用户ID
     * @throws Exception
     */
    void onLine(Long clientId) throws Exception;

    /**
     * 获取某个用户的路有关系
     * @param clientId
     * @return 获取某个用户的路有关系
     */
    RouteInfo loadRouteRelatedByClientId(Long clientId) throws Exception;

    /**
     * 推送消息
     * @param routeInfo
     * @param receiveClientId 接收客户端id
     * @param msgObject 消息
     * @throws Exception
     */
    void pushMsg(RouteInfo routeInfo,long receiveClientId ,Object msgObject) throws Exception;
}
