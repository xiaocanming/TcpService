package com.xcm.tcpservice.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @描述 TCP服务器登录返回实体类
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TcpServerResVO implements Serializable {
    @ApiModelProperty(required=false, value="tcp服务器ip", example = "127.0.0.1")
    private String ip ;

    @ApiModelProperty(required=false, value="tcp服务器端口", example = "11011")
    private Integer tcpServerPort;

    @ApiModelProperty(required=false, value="客户端id", example = "1234567890")
    private Long clientId ;
}
