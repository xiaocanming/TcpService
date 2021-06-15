package com.xcm.tcpservice.schedule.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xcm.tcpservice.common.service.ScheduleService;
import com.xcm.tcpservice.schedule.queque.DoubleBufferQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/5/31
 */
@Service(loadbalance = "random",timeout = 50000,cluster = "failsafe")
@Component
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private DoubleBufferQueue queue;

    @Override
    public String saveData(String key) {
        while (queue.getWriteListSize() >= 10000) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        queue.push(key);
        return null;
    }
}
