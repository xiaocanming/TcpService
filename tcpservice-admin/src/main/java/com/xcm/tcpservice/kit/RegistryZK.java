package com.xcm.tcpservice.kit;

import com.xcm.tcpservice.config.AppConfiguration;
import com.xcm.tcpservice.util.SpringBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @描述 注册zookeeper
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
public class RegistryZK implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(RegistryZK.class);

    private ZKit zKit;

    private AppConfiguration appConfiguration ;

    private String ip;
    private int tcpServerPort;
    private int httpPort;

    public RegistryZK(String ip, int tcpServerPort,int httpPort) {
        this.ip = ip;
        this.tcpServerPort = tcpServerPort;
        this.httpPort = httpPort ;
        zKit = SpringBeanFactory.getBean(ZKit.class) ;
        appConfiguration = SpringBeanFactory.getBean(AppConfiguration.class) ;
    }

    @Override
    public void run() {
//        try {
//            InetAddress ip4 = Inet4Address.getLocalHost();
//            System.out.println(ip4.getHostAddress());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
        //创建父节点
        zKit.createRootNode();

        //是否要将自己注册到 ZK
        if (appConfiguration.isZkSwitch()){
            String path = appConfiguration.getZkRoot() + "/ip-" + ip + ":" + tcpServerPort + ":" + httpPort;
            zKit.createNode(path);
            logger.info("Registry zookeeper success, msg=[{}]", path);
        }

    }
}
