package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Store;
import com.yuegou.entity.User;
import com.yuegou.service.StoreService;
import com.yuegou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/demos")
public class demoController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Result demos(@DateTimeFormat(pattern = "yyyy-MM-dd mm-hh-ss") Date date){
        System.out.println(date);
        return new Result(666,date);
    }

}
