package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.GoodEvaluate;
import com.yuegou.service.GoodEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluates")
public class GoodEvaluateController {

    @Autowired
    private GoodEvaluateService goodEvaluateService;

    @PostMapping
    public Result save(@RequestBody GoodEvaluate goodEvaluate){
        boolean flag = goodEvaluateService.insert(goodEvaluate);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = goodEvaluateService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody GoodEvaluate goodEvaluate){
        System.out.println(goodEvaluate);
        boolean flag = goodEvaluateService.update(goodEvaluate);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        GoodEvaluate goodEvaluate = goodEvaluateService.queryById(id);
        return new Result(goodEvaluate != null ? Code.SELECT_OK : Code.SELECT_ERR, goodEvaluate, goodEvaluate != null ? "OK" : "Error");
    }

    @GetMapping
    public Result getAll(){
        List<GoodEvaluate> goodEvaluateList = goodEvaluateService.queryAll();
        return new Result(goodEvaluateList != null ? Code.SELECT_OK : Code.SELECT_ERR, goodEvaluateList, goodEvaluateList != null ? "OK" : "Error");
    }

}
