package com.xcm.tcpservice.common.algorithm.random;

import com.xcm.tcpservice.common.algorithm.RouteHandle;
import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.exception.TcpException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @描述 路由策略，随机
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
public class RandomHandle implements RouteHandle {
    @Override
    public String routeServer(List<String> values, String key) {
        int size = values.size();
        if (size == 0) {
            throw new TcpException(StatusEnum.SERVER_NOT_AVAILABLE) ;
        }
        int offset = ThreadLocalRandom.current().nextInt(size);

        return values.get(offset);
    }
}
