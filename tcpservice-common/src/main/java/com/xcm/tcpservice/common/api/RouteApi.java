package com.xcm.tcpservice.common.api;

import com.xcm.tcpservice.common.pojo.ClientStatusReqVO;
import com.xcm.tcpservice.common.pojo.LoginReqVO;
import com.xcm.tcpservice.common.pojo.SendMsgReqVO;

/**
 * @描述 路由api
 * @创建人 xcm
 * @创建时间 2021/3/4
 */
public interface RouteApi {

    /**
     * 客户端端下线
     *
     * @param clientStatusReqVO
     * @return
     * @throws Exception
     */
    Object offLine(ClientStatusReqVO clientStatusReqVO) throws Exception;

    /**
     * 客户端上线
     *
     * @param clientStatusReqVO
     * @return
     * @throws Exception
     */
    Object onLine(ClientStatusReqVO clientStatusReqVO) throws Exception;

    /**
     * 通知服务端发送消息
     *
     * @param sendMsgReqVO
     * @return
     * @throws Exception
     */
    Object sendMsg(SendMsgReqVO sendMsgReqVO) throws Exception;

}
