package com.xcm.tcpservice;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/24
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
