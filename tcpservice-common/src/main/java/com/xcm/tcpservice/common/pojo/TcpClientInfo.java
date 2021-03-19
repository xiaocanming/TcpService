package com.xcm.tcpservice.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @描述 客户端信息实体类
 * @创建人 xcm
 * @创建时间 2021/3/3
 */
@Data
public class TcpClientInfo {
    //客户端id
    private Long clientid ;
    // 客户端id
    private String clientmodel ;
    // 客户端名称
    private String clientnumber ;
    // 客户端名称
    private String clientname ;
    // 客户端名称
    private String clienttype ;
    //客户端权限
    private List<String> clientsecurity;

}
