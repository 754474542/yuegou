package com.yuegou.dao;

import com.yuegou.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Category)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Mapper
public interface CategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    Category queryById(Long categoryId);

    Category queryByIdAllStyle(Long categoryId);

    List<Category> queryAllSecondary();


    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    boolean insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    boolean update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 影响行数
     */
    boolean deleteById(Long categoryId);

}

