package com.xcm.tcpservice.service.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.xcm.tcpservice.common.pojo.BaseResponse;
import com.xcm.tcpservice.common.service.JobService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/25
 */
@Service(loadbalance = "random",timeout = 50000,cluster = "failsafe")
@Component
public class JobServiceImpl implements JobService {

    @Override
    public BaseResponse<String> saveData(List<String> keys) {
        return null;
    }
}
