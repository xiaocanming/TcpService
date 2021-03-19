package com.xcm.tcpservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @描述 配置中心
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Component
@Data
public class AppConfiguration {

    @Value("${app.zk.root}")
    private String zkRoot;

    @Value("${app.zk.addr}")
    private String zkAddr;

    @Value("${app.zk.switch}")
    private boolean zkSwitch;

    @Value("${app.zk.timeout}")
    private int zkConnectTimeout;

    @Value("${tcp.server.port}")
    private int tcpServerPort;

    @Value("${tcp.server.heartbeattime}")
    private long heartBeatTime ;

    @Value("${route.url}")
    private String routeUrl ;

}
