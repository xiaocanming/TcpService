package com.xcm.tcpservice.schedule.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.cache.LoadingCache;
import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.pojo.BaseResponse;
import com.xcm.tcpservice.common.service.JobService;
import com.xcm.tcpservice.schedule.service.KeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @描述 key值服务类
 * @创建人 xcm
 * @创建时间 2021/4/30
 */
@Service
public class KeysServiceImpl  implements KeysService {

    @Reference  //注入要调用的服务
    private JobService jobService;

    @Override
    public boolean sendKyeToJob(List<String> keyList) {
        BaseResponse<String> response= jobService.saveData(keyList);
        if(response.getCode()=="200"){
            return true;
        }else {

        }
        return  false;
    }
}
