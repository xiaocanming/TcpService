package com.xcm.tcpservice.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @描述 客户端状态接口参数
 * @创建人 xcm
 * @创建时间 2021/3/15
 */
@Data
public class ClientStatusReqVO extends BaseRequest{
    @NotNull(message = "clientId 不能为空")
    @ApiModelProperty(required = true, value = "clientId", example = "1545574049323")
    private Long clientId ;
}
