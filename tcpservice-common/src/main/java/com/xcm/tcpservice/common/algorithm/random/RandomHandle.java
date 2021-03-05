package com.xcm.tcpservice.common.algorithm.random;

import com.xcm.tcpservice.common.algorithm.RouteHandle;
import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.exception.TcpException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Function: 路由策略， 随机
 *
 * @Auther: jiangyunxiong
 * @Date: 2019/3/7 11:56
 * @since JDK 1.8
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
