package com.xcm.tcpservice.common.exception;

import com.xcm.tcpservice.common.enums.StatusEnum;

/**
 * @描述 自定义异常
 * @创建人 xcm
 * @创建时间 2021/3/4
 */
public class TcpException extends GenericException {


    public TcpException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public TcpException(Exception e, String errorCode, String errorMessage) {
        super(e, errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public TcpException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public TcpException(StatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.errorMessage = statusEnum.message();
        this.errorCode = statusEnum.getCode();
    }

    public TcpException(StatusEnum statusEnum, String message) {
        super(message);
        this.errorMessage = message;
        this.errorCode = statusEnum.getCode();
    }

    public TcpException(Exception oriEx) {
        super(oriEx);
    }

    public TcpException(Throwable oriEx) {
        super(oriEx);
    }

    public TcpException(String message, Exception oriEx) {
        super(message, oriEx);
        this.errorMessage = message;
    }

    public TcpException(String message, Throwable oriEx) {
        super(message, oriEx);
        this.errorMessage = message;
    }

    //如果一端的Socket被关闭（或主动关闭，或因为异常退出而 引起的关闭），另一端仍发送数据，发送的第一个数据包引发该异常(Connect reset by peer)
    public static boolean isResetByPeer(String msg) {
        if ("Connection reset by peer".equals(msg)) {
            return true;
        }
        return false;
    }

}

