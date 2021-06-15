package com.xcm.tcpservice.schedule;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/4/30
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
    }
}
