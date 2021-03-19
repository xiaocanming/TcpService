package com.xcm.tcpservice.route;

import com.xcm.tcpservice.route.config.AppConfiguration;
import com.xcm.tcpservice.route.kit.ServerListListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/2
 */
@SpringBootApplication
public class RouteApplication implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(RouteApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RouteApplication.class, args);
        LOGGER.info("Start cim route success!!!");
    }

    @Override
    public void run(String... args) throws Exception {
        //监听服务  监听到子节点的更新则更新ServerCache
        Thread thread = new Thread(new ServerListListener());
        thread.setName("zk-listener");
        thread.start() ;
    }
}