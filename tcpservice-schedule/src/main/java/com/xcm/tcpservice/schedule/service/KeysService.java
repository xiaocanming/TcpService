package com.xcm.tcpservice.schedule.service;

import java.util.List;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/25
 */
public interface KeysService {
    boolean sendKyeToJob(List<String> keyList);
}
