
package com.yuegou.controller.pretreatment.exceptionhandle;

public class FileFailedException extends RuntimeException{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public FileFailedException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public FileFailedException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }
}
