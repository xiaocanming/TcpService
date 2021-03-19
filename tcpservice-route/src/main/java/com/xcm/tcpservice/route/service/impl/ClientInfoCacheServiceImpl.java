package com.xcm.tcpservice.route.service.impl;

import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.exception.TcpException;
import com.xcm.tcpservice.common.pojo.BaseRequest;
import com.xcm.tcpservice.common.pojo.LoginReqVO;
import com.xcm.tcpservice.common.service.RedisService;
import com.xcm.tcpservice.route.service.ClientInfoCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.xcm.tcpservice.common.constant.DefaultConstants.ACCOUNTID_PREFIX;
import static com.xcm.tcpservice.common.constant.DefaultConstants.ROUTE_PREFIX;
import static com.xcm.tcpservice.common.enums.StatusEnum.ACCOUNT_NOT_MATCH;


/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/8
 */
@Service
public class ClientInfoCacheServiceImpl implements ClientInfoCacheService {

    @Autowired
    private RedisService redisService ;

    @Override
    public long login(LoginReqVO loginReqVO) throws Exception {
        long clientId = System.currentTimeMillis();
        String key = ACCOUNTID_PREFIX + clientId;
        String modelNumber=loginReqVO.getModelNumber();
        Object oldkey = redisService.get(modelNumber);
        if (null == oldkey) {
            //为了方便查询，冗余一份
            redisService.set(key,modelNumber);
            redisService.set(modelNumber,key);
        } else {
            clientId = Long.parseLong(oldkey.toString().split(":")[1]);
        }
        return clientId;
    }

    @Override
    public long loadCientIdByBaseRequest(BaseRequest request) throws Exception{
        String modelNumber=request.getModelNumber();
        Object oldkey = redisService.get(modelNumber);
        if (null == oldkey) {
            throw new TcpException(ACCOUNT_NOT_MATCH);
        }
        return  Long.parseLong(oldkey.toString().split(":")[1]);
    }
}
