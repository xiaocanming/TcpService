package com.xcm.tcpservice.route.kit;

import com.xcm.tcpservice.common.util.SpringBeanFactory;
import com.xcm.tcpservice.route.config.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        zkUtil = SpringBeanFactory.getBean(ZKit.class) ;
        appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class) ;
    }

    @Override
    public void run() {
        //注册监听服务
        zkUtil.subscribeEvent(appConfiguration.getZkRoot());

    }
}
