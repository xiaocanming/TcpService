package com.xcm.tcpservice.common.pojo;
import com.xcm.tcpservice.common.enums.StatusEnum;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @描述 Response基础类
 * @创建人 xcm
 * @创建时间 2021/3/10
 */
@Data
public class BaseResponse<T> implements Serializable{
	@ApiModelProperty(required=false, value="唯一请求号", example = "1234567890")
	private String reqNo;

	@ApiModelProperty(required=false, value="唯一请求号", example = "1234567890")
	private String code;

	@ApiModelProperty(required=false, value="唯一请求号", example = "1234567890")
	private String message;

	@ApiModelProperty(required=false, value="唯一请求号", example = "1234567890")
	private T dataBody;
}
