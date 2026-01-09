package com.cat.common.exception;

/* loaded from: hsa-ims-common-ZJ-330000-V1.1.0-RELEASE.jar:cn/hsa/ims/common/exception/ImsException.class */
public class CatException extends RuntimeException {
    private int code;

    public CatException(int code) {
        super(ExceptionConfigReader.getExceptionMessage(Integer.valueOf(code)));
        this.code = -1;
        setCode(code);
    }

    public CatException(String message) {
        super(message);
        this.code = -1;
    }

    public CatException(int code, String[] dynaValues) {
        super(ExceptionConfigReader.getExceptionMessage(Integer.valueOf(code), dynaValues));
        this.code = -1;
        setCode(code);
    }

    public CatException(int code, String message) {
        super(message);
        this.code = -1;
        setCode(code);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}