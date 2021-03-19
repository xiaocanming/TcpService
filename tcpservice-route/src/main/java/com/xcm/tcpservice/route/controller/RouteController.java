package com.xcm.tcpservice.route.controller;

import com.xcm.tcpservice.common.algorithm.RouteHandle;
import com.xcm.tcpservice.common.api.RouteApi;
import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.exception.TcpException;
import com.xcm.tcpservice.common.pojo.*;
import com.xcm.tcpservice.common.util.NetAddressUtil;
import com.xcm.tcpservice.common.util.RouteInfoParseUtil;
import com.xcm.tcpservice.route.cache.TcpServerCache;
import com.xcm.tcpservice.route.service.RouteInfoService;
import com.xcm.tcpservice.route.service.ClientInfoCacheService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @描述 路由 Controller
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Controller
@RequestMapping("/")
public class RouteController  implements RouteApi{

    private final static Logger LOGGER = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private ClientInfoCacheService clientInfoCacheService ;

    @Autowired
    private RouteInfoService routeInfoService;

    @ApiOperation("客户端上线")
    @RequestMapping(value = "onLine", method = RequestMethod.POST)
    @ResponseBody()
    @Override
    public BaseResponse<NULLBody> onLine(@RequestBody ClientStatusReqVO clientStatusReqVO) throws Exception {
        BaseResponse<NULLBody> res = new BaseResponse();

        //获取当前用户信息clientId
//        long clientId = clientInfoCacheService.loadCientIdByBaseRequest(clientStatusReqVO);

        LOGGER.info("user [{}] onLine!", clientStatusReqVO.toString());
        routeInfoService.onLine(clientStatusReqVO.getClientId());

        res.setCode(StatusEnum.SUCCESS.getCode());
        res.setMessage(StatusEnum.SUCCESS.getMessage());
        return res;
    }

    @ApiOperation("客户端下线")
    @RequestMapping(value = "offLine", method = RequestMethod.POST)
    @ResponseBody()
    @Override
    public BaseResponse<NULLBody> offLine(@RequestBody ClientStatusReqVO clientStatusReqVO) throws Exception {
        BaseResponse<NULLBody> res = new BaseResponse();

        //获取当前用户信息clientId
//        long clientId = clientInfoCacheService.loadCientIdByBaseRequest(clientStatusReqVO);

        LOGGER.info("user [{}] offline!", clientStatusReqVO.toString());
        routeInfoService.offLine(clientStatusReqVO.getClientId());

        res.setCode(StatusEnum.SUCCESS.getCode());
        res.setMessage(StatusEnum.SUCCESS.getMessage());
        return res;
    }

    @ApiOperation("发送消息")
    @RequestMapping(value = "sendMsg", method = RequestMethod.POST)
    @ResponseBody()
    @Override
    public BaseResponse<NULLBody> sendMsg(@RequestBody SendMsgReqVO sendMsgReqVO) throws Exception {
        BaseResponse<NULLBody> res = new BaseResponse();

        try {
            //获取接收消息用户的路由信息
            RouteInfo routeInfo = routeInfoService.loadRouteRelatedByClientId(sendMsgReqVO.getReceiveClientId());

            //p2pRequest.getReceiveUserId()==>消息接收者的 userID
            routeInfoService.pushMsg(routeInfo ,sendMsgReqVO.getReceiveClientId(),sendMsgReqVO.getMsg());

            res.setCode(StatusEnum.SUCCESS.getCode());
            res.setMessage(StatusEnum.SUCCESS.getMessage());

        }catch (TcpException e){
            res.setCode(e.getErrorCode());
            res.setMessage(e.getErrorMessage());
        }
        return res;
    }


}
