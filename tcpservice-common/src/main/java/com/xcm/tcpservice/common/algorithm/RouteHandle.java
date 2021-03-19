package com.xcm.tcpservice.common.algorithm;

import java.util.List;

/**
 * @描述 路由Handle
 * @创建人 xcm
 * @创建时间 2021/3/4
 */
public interface RouteHandle {
    /**
     * 在一批服务器里进行路由
     * @param values 所有的服务器
     * @param key 客户端类型
     * @return
     */
    String routeServer(List<String> values, String key) ;
}
