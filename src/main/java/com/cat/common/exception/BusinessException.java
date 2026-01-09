package com.cat.common.exception;

/*
 * @Description TODO
 * @author huanghm
 * @Date 2025-09-23 10:06
 **/
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -3232706417844019328L;
    int code = -1;

    public BusinessException() {
    }

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
