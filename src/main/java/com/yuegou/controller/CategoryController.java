package com.yuegou.controller;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.Result;
import com.yuegou.entity.Category;
import com.yuegou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorys")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result save(@RequestBody Category category){
        boolean flag = categoryService.insert(category);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boolean flag = categoryService.deleteById(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }
    @PutMapping
    public Result update(@RequestBody Category category){
        boolean flag = categoryService.update(category);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        Category category = categoryService.queryById(id);
        return new Result(category != null ? Code.SELECT_OK : Code.SELECT_ERR, category, category != null ? "OK" : "Error");
    }
    @GetMapping("/queryAllSecondary")
    public Result queryAllSecondary(){
        List<Category> categories = categoryService.queryAllSecondary();
        return new Result(categories != null ? Code.SELECT_OK : Code.SELECT_ERR, categories, categories != null ? "OK" : "Error");
    }

}
