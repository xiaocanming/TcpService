package com.xcm.tcpservice.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @描述 发送消息
 * @创建人 xcm
 * @创建时间 2021/3/10
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SendMsgReqVO extends BaseRequest {

    @ApiModelProperty(required = true, value = "clientId", example = "11")
    private Long clientId ;

    @NotNull(message = "userId 不能为空")
    @ApiModelProperty(required = true, value = "消息接收者的 clientId", example = "1545574049323")
    private Long receiveClientId ;

    @NotNull(message = "msg 不能为空")
    @ApiModelProperty(required = true, value = "msg", example = "hello")
    private Object msg ;

}
