package com.xcm.tcpservice.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xcm.tcpservice.common.constant.DefaultConstants;
import com.xcm.tcpservice.common.protocol.CIMRequestProto;
import okhttp3.OkHttpClient;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @描述 初始化bean
 * @创建人 xcm
 * @创建时间 2021/3/5
 */
@Configuration
public class BeanConfig {
    @Autowired
    private AppConfiguration appConfiguration ;

    @Bean
    public ZkClient buildZKClient(){
        return new ZkClient(appConfiguration.getZkAddr(), appConfiguration.getZkConnectTimeout());
    }

    /**
     * http client
     * @return okHttp
     */
    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    /**
     * 创建心跳单例
     * @return
     */
    @Bean(value = "heartBeat")
    public CIMRequestProto.CIMReqProtocol heartBeat() {
        CIMRequestProto.CIMReqProtocol heart = CIMRequestProto.CIMReqProtocol.newBuilder()
                .setRequestId(0L)
                .setReqMsg("pong")
                .setType(DefaultConstants.CommandType.PING)
                .build();
        return heart;
    }
}
