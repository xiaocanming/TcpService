package com.xcm.tcpservice.route.cache;

import com.google.common.cache.LoadingCache;
import com.xcm.tcpservice.route.kit.ZKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @描述 服务器节点缓存
 * @创建人 xcm
 * @创建时间 2021/3/2
 */
@Component
public class TcpServerCache {
    private static Logger logger = LoggerFactory.getLogger(TcpServerCache.class) ;

    @Autowired
    private LoadingCache<String, String> cache;

    @Autowired
    private ZKit zkUtil;

    /**
     * 新增缓存
     * @param key
     */
    public void addCache(String key) {
        cache.put(key, key);
    }

    /**
     * 更新所有缓存/先删除 再新增
     * @param currentChildren
     */
    public void updateCache(List<String> currentChildren) {
        cache.invalidateAll();
        for (String currentChild : currentChildren) {
            // currentChildren=ip-127.0.0.1:11212:9082 or 127.0.0.1:11212:9082
            String key ;
            if (currentChild.split("-").length == 2){
                key = currentChild.split("-")[1];
            }else {
                key = currentChild ;
            }
            addCache(key);
        }
    }


    /**
     * 获取所有的服务列表
     * @return
     */
    public List<String> getServerList() {
        List<String> list = new ArrayList<>();
        if (cache.size() == 0) {
            List<String> allNode = zkUtil.getAllNode();
            for (String node : allNode) {
                String key = node.split("-")[1];
                addCache(key);
            }
        }
        for (Map.Entry<String, String> entry : cache.asMap().entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }

    /**
     * 重构缓存列表
     */
    public void rebuildCacheList(){
        updateCache(getServerList()) ;
    }
}
