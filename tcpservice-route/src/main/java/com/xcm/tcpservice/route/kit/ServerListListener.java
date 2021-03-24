package com.xcm.tcpservice.route.kit;

import com.xcm.tcpservice.route.config.AppConfiguration;
import com.xcm.tcpservice.route.util.SpringBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @描述 路由服务启动
 * @创建人 xcm
 * @创建时间 2021/3/2
 */
public class ServerListListener implements Runnable{

    private static Logger logger = LoggerFactory.getLogger(ServerListListener.class);


    private ZKit zkUtil;

    private AppConfiguration appConfiguration ;

    public ServerListListener() {
        appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class) ;
        zkUtil = SpringBeanFactory.getBean(ZKit.class) ;
    }

    @Override
    public void run() {
        //注册监听服务
        zkUtil.subscribeEvent(appConfiguration.getZkRoot());
    }
}

