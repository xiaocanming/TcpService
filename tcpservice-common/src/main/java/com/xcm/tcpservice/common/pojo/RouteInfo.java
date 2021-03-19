package com.xcm.tcpservice.common.pojo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @描述 路由信息实体类
 * @创建人 xcm
 * @创建时间 2021/3/10
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public final class RouteInfo {
    private String ip ;
    private Integer tcpServerPort;
    private Integer httpPort;
}
