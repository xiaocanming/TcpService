package com.xcm.tcpservice.route.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @描述 配置类
 * @创建人 xcm
 * @创建时间 2021/3/2
 */
@Component
@Data
public class AppConfiguration {
    @Value("${app.zk.root}")
    private String zkRoot;

    @Value("${app.zk.addr}")
    private String zkAddr;

    @Value("${app.zk.timeout}")
    private int zkConnectTimeout;

    @Value("${server.port}")
    private int port;

    @Value("${app.route.way}")
    private String routeWay;

}
