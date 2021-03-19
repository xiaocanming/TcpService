package com.xcm.tcpservice.common.pojo;
/**
 * @描述 空对象,用在泛型中,表示没有额外的请求参数或者返回参数
 * @创建人 xcm
 * @创建时间 2021/3/10
 */
public class NULLBody {
    public NULLBody() {}

    public static NULLBody create(){
        return new NULLBody();
    }
}
