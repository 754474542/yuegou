package com.yuegou.controller.pretreatment.exceptionhandle;

public class PathJumpException extends RuntimeException{

    private Integer code;

    public PathJumpException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public PathJumpException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
