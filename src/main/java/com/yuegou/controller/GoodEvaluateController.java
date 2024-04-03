package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.GoodEvaluate;
import com.yuegou.service.GoodEvaluateService;
import com.yuegou.utils.PaginationUtil;
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

    @GetMapping("/{spuId}")
    public Result getById(@PathVariable Long spuId){
        List<GoodEvaluate> goodEvaluateList = goodEvaluateService.queryBySpuId(spuId);
        return new Result(goodEvaluateList != null ? Code.SELECT_OK : Code.SELECT_ERR, goodEvaluateList, goodEvaluateList != null ? "OK" : "Error");
    }

    @GetMapping
    public Result getAll(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page, @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<GoodEvaluate> goodEvaluateList = goodEvaluateService.queryAll(size,offset);
        return new Result(goodEvaluateList != null ? Code.SELECT_OK : Code.SELECT_ERR, goodEvaluateList, goodEvaluateList != null ? "OK" : "Error");
    }

    @GetMapping("/queryBySpuIdLimit")
    public Result queryBySpuIdAllLimit(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page, @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size,@RequestParam Long spuId){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<GoodEvaluate> goodEvaluateList = goodEvaluateService.queryBySpuIdLimit(size,offset,spuId);
        return new Result(goodEvaluateList != null ? Code.SELECT_OK : Code.SELECT_ERR, goodEvaluateList, goodEvaluateList != null ? "OK" : "Error");
    }

}
