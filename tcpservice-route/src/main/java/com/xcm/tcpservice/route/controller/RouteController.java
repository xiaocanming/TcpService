package com.xcm.tcpservice.route.controller;

import com.xcm.tcpservice.common.api.RouteApi;
import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.pojo.BaseResponse;
import com.xcm.tcpservice.common.pojo.NULLBody;
import com.xcm.tcpservice.common.pojo.TcpClientInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Controller
@RequestMapping("/")
public class RouteController  {

    private final static Logger LOGGER = LoggerFactory.getLogger(RouteController.class);

//    @ApiOperation("客户端下线")
//    @RequestMapping(value = "offLine", method = RequestMethod.POST)
//    @ResponseBody()
//    @Override
//    public BaseResponse<NULLBody> offLine(@RequestBody ChatReqVO groupReqVO) throws Exception {
//        BaseResponse<NULLBody> res = new BaseResponse();
//
//        TcpClientInfo cimUserInfo = userInfoCacheService.loadUserInfoByUserId(groupReqVO.getUserId());
//
//        LOGGER.info("user [{}] offline!", cimUserInfo.toString());
//        accountService.offLine(groupReqVO.getUserId());
//
//        res.setCode(StatusEnum.SUCCESS.getCode());
//        res.setMessage(StatusEnum.SUCCESS.getMessage());
//        return res;
//    }
//
//    /**
//     * 获取一台 CIM server
//     *
//     * @return
//     */
//    @ApiOperation("登录并获取服务器")
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    @ResponseBody()
//    @Override
//    public BaseResponse<CIMServerResVO> login(@RequestBody LoginReqVO loginReqVO) throws Exception {
//        BaseResponse<CIMServerResVO> res = new BaseResponse();
//
//        // check server available
//        String server = routeHandle.routeServer(serverCache.getServerList(),String.valueOf(loginReqVO.getUserId()));
//        LOGGER.info("userName=[{}] route server info=[{}]", loginReqVO.getUserName(), server);
//
//        RouteInfo routeInfo = RouteInfoParseUtil.parse(server);
//        commonBizService.checkServerAvailable(routeInfo);
//
//        //登录校验
//        StatusEnum status = accountService.login(loginReqVO);
//        if (status == StatusEnum.SUCCESS) {
//
//            //保存路由信息
//            accountService.saveRouteInfo(loginReqVO,server);
//
//            CIMServerResVO vo = new CIMServerResVO(routeInfo);
//            res.setDataBody(vo);
//
//        }
//        res.setCode(status.getCode());
//        res.setMessage(status.getMessage());
//
//        return res;
//    }

}
