package com.xcm.tcpservice.route.config;

import com.xcm.tcpservice.common.config.BaseSwaggerConfig;
import com.xcm.tcpservice.common.pojo.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @描述 Swagger2API文档的配置
 * @创建人 xcm
 * @创建时间 2021/3/4
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {

        return SwaggerProperties.builder()
                .apiBasePackage("com.xcm.smallmall.controller") //指定扫描的包
                .title("smallmall后台系统")
                .description("smallmall后台相关接口文档")
                .contactName("xcm")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }
}
