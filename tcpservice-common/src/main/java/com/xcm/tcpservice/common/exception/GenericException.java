package com.xcm.tcpservice.common.exception;

import java.io.Serializable;

/**
 * @描述 自定义异常
 * @创建人 xcm
 * @创建时间 2021/3/10
 */
public class GenericException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    String errorCode;
    String errorMessage;

    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(Exception oriEx) {
        super(oriEx);
    }

    public GenericException(Exception oriEx, String message) {
        super(message, oriEx);
    }

    public GenericException(Throwable oriEx) {
        super(oriEx);
    }

    public GenericException(String message, Exception oriEx) {
        super(message, oriEx);
    }

    public GenericException(String message, Throwable oriEx) {
        super(message, oriEx);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}