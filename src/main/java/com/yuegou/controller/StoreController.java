package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Store;
import com.yuegou.service.StoreService;
import com.yuegou.utils.PaginationUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Store)表控制层
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@RestController
@RequestMapping("/stores")
public class StoreController {
    /**
     * 服务对象
     */
    @Resource
    private StoreService storeService;

    @Autowired
    private Logger logger;

    @GetMapping
    private Result queryAll(@RequestParam(value = "page",defaultValue = "#{paginationConfig.page}") Integer page, @RequestParam(value = "size", defaultValue = "#{paginationConfig.size}") Integer size){
        int offset = PaginationUtil.calculateOffset(page, size);
        List<Store> stores = storeService.queryAll(size,offset);
        return new Result(stores != null ? Code.SELECT_OK : Code.SELECT_ERR, stores, stores != null ? "OK" : "Error");
    }

    /**
     * 通过主键ID查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Long id) {
        Store store = storeService.queryById(id);
        return new Result(store != null ? Code.SELECT_OK : Code.SELECT_ERR, store, store != null ? "OK" : "Error");
    }

    /**
     * 新增数据
     *
     * @param store 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Store store) {
        boolean insert = storeService.insert(store);
        if (insert) logger.info("欢迎 " + store.getStoreName() + " 入驻商家");
        return new Result(insert ? Code.SAVE_OK : Code.SAVE_ERR, store, insert ? "OK" : "Error");
    }

    /**
     * 编辑数据
     *
     * @param store 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody Store store) {
        boolean update = storeService.update(store);
        return new Result(update ? Code.UPDATE_OK : Code.UPDATE_ERR, store, update ? "OK" : "Error");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean delete = storeService.delete(id);
        return new Result(delete ? Code.DELETE_OK : Code.DELETE_ERR, delete, delete ? "OK" : "Error");
    }

}

