package com.yuegou.controller.pretreatment.exceptionhandle;

public class CURDException extends RuntimeException{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CURDException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CURDException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }
}
