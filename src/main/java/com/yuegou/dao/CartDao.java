package com.yuegou.dao;

import com.yuegou.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Cart)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Mapper
public interface CartDao {

    Cart queryByUserId(Long userId);

    /**
     * 通过主键删除数据
     *
     * @param cartId 主键
     * @return 影响行数
     */
    int deleteById(Long cartId);

}

