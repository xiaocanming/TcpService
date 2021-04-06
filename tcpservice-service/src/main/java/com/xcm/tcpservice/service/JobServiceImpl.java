package com.xcm.tcpservice.service;

import com.xcm.tcpservice.common.service.JobService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/25
 */
@Service(loadbalance = "random",timeout = 50000,cluster = "failsafe")
public class JobServiceImpl implements JobService {

    @Override
    public String saveData() throws InterruptedException {

        return "Hello Dubbo";
    }
}
