package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Detail;
import com.yuegou.entity.Order;
import com.yuegou.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    private DetailService detailService;


    @GetMapping("/{id}")
    public Result queryByOrderId(@PathVariable Long id){
        List<Detail> details = detailService.queryByOrderId(id);
        return new Result(details != null ? Code.SELECT_OK : Code.SELECT_ERR,details,details != null ? "OK" : "ERROR");
    }

    @PostMapping
    public Result insert(@RequestBody Detail detail){
        boolean flag = detailService.insert(detail);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR,flag ? "OK" : "ERROR");
    }

    @PutMapping
    public Result update(@RequestBody Detail detail){
        boolean flag = detailService.update(detail);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag,flag ? "OK" : "Error");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = detailService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR,flag,flag ? "OK" : "Error");
    }

}
