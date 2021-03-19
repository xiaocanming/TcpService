package com.xcm.tcpservice.route.config;

import com.xcm.tcpservice.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @描述 Redis配置类
 * @创建人 xcm
 * @创建时间 2020/12/3
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}