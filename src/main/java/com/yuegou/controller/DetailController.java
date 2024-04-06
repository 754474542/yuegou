package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Detail;
import com.yuegou.entity.Order;
import com.yuegou.service.DetailService;
import com.yuegou.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/details")
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
        System.out.println(detail);
        boolean flag = detailService.update(detail);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag,flag ? "OK" : "Error");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = detailService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR,flag,flag ? "OK" : "Error");
    }

    @GetMapping("/queryByStoreId")
    public Result queryByStoreId(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page,
                                 @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size,
                                 @RequestParam("storeId") Long storeId,
                                 @RequestParam("detailStatus")Integer detailStatus){
        int offset = PaginationUtil.calculateOffset(page,size);
        List<Detail> details = detailService.queryByStoreId(size, offset, storeId, detailStatus);
        return new Result(details != null ? Code.SELECT_OK : Code.SELECT_ERR,details,details != null ? "OK" : "ERROR");
    }

}
