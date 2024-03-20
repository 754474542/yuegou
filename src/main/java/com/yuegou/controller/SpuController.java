package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Spu;
import com.yuegou.entity.SpuAndAttributeValues;
import com.yuegou.entity.SpuAttributeValue;
import com.yuegou.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/spus")
public class SpuController {

    @Autowired
    private SpuService spuService;

    //这里一次性接收Spu,SpuAttributeValue,Attribute,因为他们之间互相有关联，所以放在一起存储。
    @PostMapping
    public Result saveSpuAndAttributeValues(@RequestBody SpuAndAttributeValues spuAndAttributeValues){
        boolean flag = spuService.saveSpuAndAttributeValues(spuAndAttributeValues);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = spuService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody SpuAndAttributeValues spuAndAttributeValues){
        boolean flag = spuService.update(spuAndAttributeValues);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @GetMapping
    public Result queryAll(){
        List<Spu> spus = spuService.queryAll();
        return new Result(spus != null ? Code.SELECT_OK : Code.SELECT_ERR, spus, spus != null ? "OK" : "Error");
    }

    @GetMapping("/{id}")
    public Result queryBySpuId(@PathVariable Long id){
        Spu spu = spuService.queryBySpuId(id);
        return new Result(spu != null ? Code.SELECT_OK : Code.SELECT_ERR, spu, spu != null ? "OK" : "Error");
    }

}
