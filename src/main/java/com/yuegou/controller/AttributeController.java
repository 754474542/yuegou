package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Attribute;
import com.yuegou.entity.User;
import com.yuegou.service.AttributeService;
import com.yuegou.service.UserService;
import com.yuegou.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attributes")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @PostMapping
    public Result save(@RequestBody Attribute attribute){
        boolean flag = attributeService.insert(attribute);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{spuId}")
    public Result delete(@PathVariable Long spuId){
        boolean flag = attributeService.deleteBySpuId(spuId);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }
    @PutMapping
    public Result update(@RequestBody Attribute attribute){
        boolean flag = attributeService.update(attribute);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }
    @GetMapping("/{spuId}")
    public Result getById(@PathVariable Long spuId){
        List<Attribute> attributes = attributeService.queryBySpuId(spuId);
        return new Result(attributes != null ? Code.SELECT_OK : Code.SELECT_ERR, attributes, attributes != null ? "OK" : "Error");
    }


}
