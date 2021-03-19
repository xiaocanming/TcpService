package com.xcm.tcpservice.common.api;

import com.xcm.tcpservice.common.pojo.ClientStatusReqVO;
import com.xcm.tcpservice.common.pojo.LoginReqVO;
import com.xcm.tcpservice.common.pojo.SendMsgReqVO;

/**
 * @描述 admin服务api
 * @创建人 xcm
 * @创建时间 2021/3/4
 */
public interface AdminApi {
    /**
     * 发送消息到客户端
     * @param sendMsgReqVO
     * @return
     * @throws Exception
     */
    Object sendMsg(SendMsgReqVO sendMsgReqVO) throws Exception;
}
