package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Order;
import com.yuegou.entity.OrderAndDetail;
import com.yuegou.service.OrderService;
import com.yuegou.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Result queryById(@PathVariable Long id){
        Order order = orderService.queryById(id);
        return new Result(order != null ? Code.SELECT_OK : Code.SELECT_ERR,order,order != null ? "OK" : "ERROR");
    }

    @GetMapping
    public Result queryAll(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page, @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<Order> orders = orderService.queryAll(size,offset);
        return new Result(orders != null ? Code.SELECT_OK : Code.SELECT_ERR,orders,orders != null ? "OK" : "ERROR");
    }

    @PostMapping
    public Result insert(@RequestBody OrderAndDetail orderAndDetail){
        boolean flag = orderService.insert(orderAndDetail);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR,flag ? "OK" : "ERROR");
    }

    @PutMapping
    public Result update(@RequestBody OrderAndDetail orderAndDetail){
        boolean flag = orderService.update(orderAndDetail);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag,flag ? "OK" : "Error");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = orderService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR,flag,flag ? "OK" : "Error");
    }

    @GetMapping("/queryUserId")
    public Result queryOrderListByUserId(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page,
                                         @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size,
                                         @RequestParam("userId") Long userId,
                                         @RequestParam("orderStatus")Integer orderStatus){
        int offset = PaginationUtil.calculateOffset(page,size);
        List<Order> orders = orderService.queryByUserId(size,offset,userId,orderStatus);
        return new Result(orders != null ? Code.SELECT_OK : Code.SELECT_ERR,orders,orders != null ? "OK" : "ERROR");
    }

}
