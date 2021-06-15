package com.xcm.tcpservice.common.service;

import com.xcm.tcpservice.common.pojo.BaseResponse;

import java.util.List;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/25
 */
public interface JobService {
    BaseResponse<String> saveData(List<String> keys);
}
