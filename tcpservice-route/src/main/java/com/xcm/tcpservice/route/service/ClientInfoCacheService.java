package com.xcm.tcpservice.route.service;

import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.pojo.BaseRequest;
import com.xcm.tcpservice.common.pojo.LoginReqVO;
import com.xcm.tcpservice.common.pojo.TcpClientInfo;

import java.util.Set;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/8
 */
public interface ClientInfoCacheService {

    /**
     * 注册客户端信息 并返回可用服务器
     * @param loginReqVO 登录信息
     * @return true 成功 false 失败
     * @throws Exception
     */
    long login(LoginReqVO loginReqVO) throws Exception ;

    /**
     * 通过 userID 获取用户信息
     * @param request 请求参数
     * @return
     * @throws Exception
     */
    long loadCientIdByBaseRequest(BaseRequest request) throws Exception;

}
