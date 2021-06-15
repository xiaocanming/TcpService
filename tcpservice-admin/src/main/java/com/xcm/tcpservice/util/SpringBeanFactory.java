package com.xcm.tcpservice.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @描述  这个需要写在项目中，不能放到common中，否则会报错，具体什么原因暂时不知道
 * @创建人 xcm
 * @创建时间 2021/3/23
 */
@Component
public final class SpringBeanFactory implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> c){
        return context.getBean(c);
    }


    public static <T> T getBean(String name,Class<T> clazz){
        return context.getBean(name,clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
