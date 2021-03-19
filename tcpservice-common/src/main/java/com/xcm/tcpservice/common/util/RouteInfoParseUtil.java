package com.xcm.tcpservice.common.util;

import com.xcm.tcpservice.common.exception.TcpException;
import com.xcm.tcpservice.common.pojo.RouteInfo;

import static com.xcm.tcpservice.common.enums.StatusEnum.VALIDATION_FAIL;

/**
 * @描述 路由信息转换工具类
 * @创建人 xcm
 * @创建时间 2021/3/11
 */
public class RouteInfoParseUtil {
    public static RouteInfo parse(String info){
        try {
            String[] serverInfo = info.split(":");
            RouteInfo routeInfo =  new RouteInfo(serverInfo[0], Integer.parseInt(serverInfo[1]),Integer.parseInt(serverInfo[2])) ;
            return routeInfo ;
        }catch (Exception e){
            throw new TcpException(VALIDATION_FAIL) ;
        }
    }
}
