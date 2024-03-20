package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @Autowired
    private Logger logger;

    @GetMapping
    public Result getErrorPath(){
        logger.error("异常错误");
        return new Result(Code.SYSTEM_ERR,"出现异常错误，请联系管理员");
    }

}
