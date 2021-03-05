package com.xcm.tcpservice.common.algorithm.loop;


import com.xcm.tcpservice.common.algorithm.RouteHandle;
import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.exception.TcpException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Function:
 *
 * @author crossoverJie
 * Date: 2019-02-27 15:13
 * @since JDK 1.8
 */
public class LoopHandle implements RouteHandle {
    private AtomicLong index = new AtomicLong();

    @Override
    public String routeServer(List<String> values,String key) {
        if (values.size() == 0) {
            throw new TcpException(StatusEnum.SERVER_NOT_AVAILABLE) ;
        }
        Long position = index.incrementAndGet() % values.size();
        if (position < 0) {
            position = 0L;
        }

        return values.get(position.intValue());
    }
}
