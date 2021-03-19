package com.xcm.tcpservice.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @描述 登录接口参数
 * @创建人 xcm
 * @创建时间 2021/3/10
 */
@Data
public class LoginReqVO extends BaseRequest{

    @ApiModelProperty(required = false, value = "客户端名称", example = "卫星1")
    private String clientName ;

    @ApiModelProperty(required = false, value = "客户端类型", example = "卫星")
    private String clientType ;

}
