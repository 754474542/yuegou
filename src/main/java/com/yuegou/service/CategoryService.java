package com.yuegou.service;

import com.yuegou.entity.Category;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Transactional
public interface CategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    Category queryById(Long categoryId);

    Category queryByIdAllStyle(Long categoryId);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    boolean deleteById(Long categoryId);

}
