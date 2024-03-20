package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Order;
import com.yuegou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Result queryById(@PathVariable Long id){
        Order order = orderService.queryById(id);
        return new Result(order != null ? Code.SELECT_OK : Code.SELECT_ERR,order,order != null ? "OK" : "ERROR");
    }

    @GetMapping
    public Result queryAll(){
        List<Order> orders = orderService.queryAll();
        return new Result(orders != null ? Code.SELECT_OK : Code.SELECT_ERR,orders,orders != null ? "OK" : "ERROR");
    }

    @PostMapping
    public Result insert(@RequestBody Order order){
        //这里后面要加Detail的业务
        boolean flag = orderService.insert(order);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR,flag ? "OK" : "ERROR");
    }

    @PutMapping
    public Result update(@RequestBody Order order){
        boolean flag = orderService.update(order);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR,flag,flag ? "OK" : "Error");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = orderService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR,flag,flag ? "OK" : "Error");
    }

}
