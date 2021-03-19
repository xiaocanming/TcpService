package com.xcm.tcpservice.route.controller;

import com.xcm.tcpservice.common.algorithm.RouteHandle;
import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.exception.TcpException;
import com.xcm.tcpservice.common.pojo.BaseResponse;
import com.xcm.tcpservice.common.pojo.LoginReqVO;
import com.xcm.tcpservice.common.pojo.RouteInfo;
import com.xcm.tcpservice.common.pojo.TcpServerResVO;
import com.xcm.tcpservice.common.util.NetAddressUtil;
import com.xcm.tcpservice.common.util.RouteInfoParseUtil;
import com.xcm.tcpservice.route.cache.TcpServerCache;
import com.xcm.tcpservice.route.service.ClientInfoCacheService;
import com.xcm.tcpservice.route.service.RouteInfoService;
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
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/18
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private TcpServerCache serverCache;

    @Autowired
    private ClientInfoCacheService clientInfoCacheService ;
    @Autowired
    private RouteInfoService routeInfoService ;

    @Autowired
    private RouteHandle routeHandle ;

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    /**
     * 获取一台 TCP server
     * @return
     */
    @ApiOperation("登录并获取服务器")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody()
    public BaseResponse<TcpServerResVO> login(@RequestBody LoginReqVO loginReqVO) throws Exception {
        BaseResponse<TcpServerResVO> res = new BaseResponse();
        // check server available
        String server = routeHandle.routeServer(serverCache.getServerList(),loginReqVO.getClientType());
        LOGGER.info("userName=[{}] route server info=[{}]", loginReqVO.getClientType(), server);

        RouteInfo routeInfo = RouteInfoParseUtil.parse(server);
        //判断sockets是否有效
        boolean reachable = NetAddressUtil.checkAddressReachable(routeInfo.getIp(), routeInfo.getTcpServerPort(), 1000);
        if (!reachable) {
            LOGGER.error("ip={}, port={} are not available", routeInfo.getIp(), routeInfo.getTcpServerPort());
            // rebuild cache
            serverCache.rebuildCacheList();
            throw new TcpException(StatusEnum.SERVER_NOT_AVAILABLE) ;
        }
        ///保存账户信息
        Long clientId = clientInfoCacheService.login(loginReqVO);
        res.setCode(StatusEnum.SUCCESS.getCode());
        res.setMessage(StatusEnum.SUCCESS.getMessage());
        TcpServerResVO vo = new TcpServerResVO(routeInfo.getIp(),routeInfo.getTcpServerPort(),clientId);
        res.setDataBody(vo);
        //保存路由信息
        routeInfoService.saveRouteInfo(clientId,server);
        return res;
    }

}
