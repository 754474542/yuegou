package com.yuegou.controller.pretreatment.exceptionhandle;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionHandler {
    @Autowired
    private Logger logger;

    @ExceptionHandler(OffSetException.class)
    public Result curdException(OffSetException ex){
        Class<? extends OffSetException> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(CURDException.class)
    public Result curdException(CURDException ex){
        Class<? extends CURDException> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(FileFailedException.class)
    public Result fileFailedException(FileFailedException ex){
        Class<? extends FileFailedException> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(ex.getCode(),ex.getMessage());
    }


    @ExceptionHandler(NullValueException.class)
    public Result nullValueException(NullValueException ex){
        Class<? extends NullValueException> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Result expiredJwtException(ExpiredJwtException ex){
        Class<? extends ExpiredJwtException> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(MyBatisException.class)
    public Result mybatisException(MyBatisException ex){
        Class<? extends MyBatisException> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    public Result loginException(LoginException ex){
        Class<? extends LoginException> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(PathJumpException.class)
    public Result pathJumpException(PathJumpException ex){
        Class<? extends PathJumpException> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result allException(Exception ex){
        ex.printStackTrace();
        Class<? extends Exception> aClass = ex.getClass();
        logger.error("出现 " + aClass + " 异常");
        return new Result(Code.SYSTEM_ERR,"出现未知错误，请联系管理员");
    }

}
