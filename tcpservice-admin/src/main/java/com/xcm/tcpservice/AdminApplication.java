package com.xcm.tcpservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.xcm.tcpservice.config.AppConfiguration;
import com.xcm.tcpservice.kit.RegistryZK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@SpringBootApplication
@EnableDubboConfiguration
public class AdminApplication implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminApplication.class);

    @Autowired
    private AppConfiguration appConfiguration ;

    @Value("${server.port}")
    private int httpPort ;

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        LOGGER.info("Start cim server success!!!");
    }

    @Override
    public void run(String... args) throws Exception {
        //获得本机IP
        String addr = InetAddress.getLocalHost().getHostAddress();
        Thread thread = new Thread(new RegistryZK(addr, appConfiguration.getTcpServerPort(),httpPort));
        thread.setName("registry-zk");
        thread.start() ;
    }
}
