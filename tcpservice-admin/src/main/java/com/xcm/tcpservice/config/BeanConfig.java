package com.xcm.tcpservice.config;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/5
 */
@Configuration
public class BeanConfig {
    @Autowired
    private AppConfiguration appConfiguration ;

    @Bean
    public ZkClient buildZKClient(){
        return new ZkClient(appConfiguration.getZkAddr(), appConfiguration.getZkConnectTimeout());
    }

}
