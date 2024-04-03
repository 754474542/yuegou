package com.yuegou.service.impl;

import com.yuegou.entity.Category;
import com.yuegou.dao.CategoryDao;
import com.yuegou.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Long categoryId) {
        return this.categoryDao.queryById(categoryId);
    }

    @Override
    public Category queryByIdAllStyle(Long categoryId) {
        return categoryDao.queryByIdAllStyle(categoryId);
    }

    @Override
    public List<Category> queryAllSecondary() {
        return categoryDao.queryAllSecondary();
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Category category) {
        return categoryDao.insert(category);
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Category category) {
        return categoryDao.update(category);
    }

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long categoryId) {
        return categoryDao.deleteById(categoryId);
    }
}
