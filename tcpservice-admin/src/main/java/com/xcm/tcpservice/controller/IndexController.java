package com.xcm.tcpservice.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xcm.tcpservice.common.api.AdminApi;
import com.xcm.tcpservice.common.enums.StatusEnum;
import com.xcm.tcpservice.common.pojo.BaseResponse;
import com.xcm.tcpservice.common.pojo.NULLBody;
import com.xcm.tcpservice.common.pojo.SendMsgReqVO;
import com.xcm.tcpservice.common.service.JobService;
import com.xcm.tcpservice.server.TcpServer;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Controller
@RequestMapping("/")
public class IndexController implements AdminApi {

    @Autowired
    private TcpServer tcpServer;

    /**
     * 推送消息到客户端
     * @param sendMsgReqVO
     * @return
     */
    @Override
    @ApiOperation("Push msg to client")
    @RequestMapping(value = "sendMsg",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<NULLBody> sendMsg(@RequestBody SendMsgReqVO sendMsgReqVO){
        BaseResponse<NULLBody> res = new BaseResponse();
        tcpServer.sendMsg(sendMsgReqVO) ;
        res.setCode(StatusEnum.SUCCESS.getCode());
        res.setMessage(StatusEnum.SUCCESS.getMessage());
        return res;
    }


    @Reference  //注入要调用的服务
    private JobService jobService;

    /**
     * 推送消息到客户端
     * @return
     */
    @ApiOperation("Push msg to client")
    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<String> test(){
        BaseResponse<String> res = new BaseResponse();
        res.setCode(StatusEnum.SUCCESS.getCode());
        res.setMessage(StatusEnum.SUCCESS.getMessage());
        res.setDataBody(jobService.saveData());
        return res;
    }

}



