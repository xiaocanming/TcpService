package com.xcm.tcpservice.common.constant;

/**
 * @描述 系统默认常量
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
public class DefaultConstants {
    /**
     * 账号前缀
     */
    public final static String ACCOUNTID_PREFIX = "client-accountid:";

    /**
     * 路由信息前缀
     */
    public final static String ROUTE_PREFIX = "client-route:";

    /**
     * 登录状态前缀
     */
    public final static String LOGIN_STATUS_PREFIX = "login-status";

    /**
     * 自定义报文类型
     */
    public static class CommandType{
        /**
         * 登录
         */
        public static final int LOGIN = 1 ;
        /**
         * 业务消息
         */
        public static final int MSG = 2 ;

        /**
         * ping
         */
        public static final int PING = 3 ;
    }
}
