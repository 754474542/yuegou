package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Sku;
import com.yuegou.entity.SkuAndAttributeValues;
import com.yuegou.service.SkuService;
import com.yuegou.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skus")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @PostMapping
    public Result insertSkuAndAttributeValues(@RequestBody SkuAndAttributeValues skuAndAttributeValues){
        boolean flag = skuService.insertSkuAndAttributeValues(skuAndAttributeValues);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = skuService.deleteById(id);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody SkuAndAttributeValues skuAndAttributeValues){
        boolean flag = skuService.update(skuAndAttributeValues);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR,flag);
    }

    @GetMapping
    public Result queryAll(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page, @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<Sku> skus = skuService.queryAll(size,offset);
        return new Result(skus != null ? Code.SELECT_OK : Code.SELECT_ERR , skus ,skus != null ? "OK" : "ERROR" );
    }

    @GetMapping("/{id}")
    public Result queryBySkuId(@PathVariable Long id){
        Sku sku = skuService.queryById(id);
        return new Result(sku != null ? Code.SELECT_OK : Code.SELECT_ERR , sku ,sku != null ? "OK" : "ERROR" );
    }

    @GetMapping("/queryBySpuId/{id}")
    public Result queryBySpuId(@PathVariable Long id){
        List<Sku> skuList = skuService.queryBySpuId(id);
        return new Result(skuList != null ? Code.SELECT_OK : Code.SELECT_ERR , skuList ,skuList != null ? "OK" : "ERROR" );
    }

}
