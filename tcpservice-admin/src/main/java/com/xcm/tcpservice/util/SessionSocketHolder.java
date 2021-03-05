package com.xcm.tcpservice.util;

import com.xcm.tcpservice.common.pojo.TcpClientInfo;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
public class SessionSocketHolder {

    private static final Map<Long, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>(16);
    private static final Map<Long, String> SESSION_MAP = new ConcurrentHashMap<>(16);

    public static void saveSession(Long userId,String userName){
        SESSION_MAP.put(userId, userName);
    }

    public static void removeSession(Long userId){
        SESSION_MAP.remove(userId) ;
    }

    /**
     * Save the relationship between the userId and the channel.
     * @param id
     * @param socketChannel
     */
    public static void put(Long id, NioSocketChannel socketChannel) {
        CHANNEL_MAP.put(id, socketChannel);
    }

    public static NioSocketChannel get(Long id) {
        return CHANNEL_MAP.get(id);
    }

    public static Map<Long, NioSocketChannel> getRelationShip() {
        return CHANNEL_MAP;
    }

    public static void remove(NioSocketChannel nioSocketChannel) {
        CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    }

    /**
     * 获取注册用户信息
     * @param nioSocketChannel
     * @return
     */
    public static TcpClientInfo getUserId(NioSocketChannel nioSocketChannel){
        for (Map.Entry<Long, NioSocketChannel> entry : CHANNEL_MAP.entrySet()) {
            NioSocketChannel value = entry.getValue();
            if (nioSocketChannel == value){
                Long key = entry.getKey();
                String userName = SESSION_MAP.get(key);
                TcpClientInfo info = new TcpClientInfo() ;
                info.setClientId(key);
                info.setClientName(userName);
                return info ;
            }
        }

        return null;
    }

}
