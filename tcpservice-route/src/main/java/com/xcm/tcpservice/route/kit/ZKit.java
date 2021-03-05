package com.xcm.tcpservice.route.kit;

import com.alibaba.fastjson.JSON;
import com.xcm.tcpservice.route.cache.ServerCache;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/2
 */
@Component
public class ZKit {
    private static Logger logger = LoggerFactory.getLogger(ZKit.class);

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private ServerCache serverCache ;


    /**
     * 监听事件
     *
     * @param path
     */
    public void subscribeEvent(String path) {
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChildren) throws Exception {
                logger.info("Clear and update local cache parentPath=[{}],currentChildren=[{}]", parentPath,currentChildren.toString());

                //update local cache, delete and save.
                serverCache.updateCache(currentChildren) ;
            }
        });


    }


    /**
     * get all server node from zookeeper
     * @return
     */
    public List<String> getAllNode(){
        List<String> children = zkClient.getChildren("/route");
        logger.info("Query all node =[{}] success.", JSON.toJSONString(children));
        return children;
    }

}
