package com.xcm.tcpservice.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @描述 请求基础类
 * @创建人 xcm
 * @创建时间 2021/3/10
 */
@Data
public class BaseRequest {

    @ApiModelProperty(required=false, value="唯一请求号", example = "1234567890")
    private String reqNo;

    @NotNull(message = "型号 不能为空")
    @ApiModelProperty(required=true, value="型号", example = "型号1")
    private String model;

    @NotNull(message = "编号 不能为空")
    @ApiModelProperty(required=true, value="编号", example = "11111")
    private String number;

    public String getModelNumber(){
        return  this.model+"-"+this.number;
    }
}
