package com.xcm.tcpservice.common.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @描述 数据模型
 * @创建人 xcm
 * @创建时间 2021/3/30
 */
@Document(collection = "datamodel")
public class DataModel {
    @Id
    private String id;

    private String name;
}
