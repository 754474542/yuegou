package com.yuegou.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.yuegou.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Order)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Mapper
public interface OrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    Order queryById(Long orderId);

    List<Order> queryByUserId(@Param("size") Integer size,@Param("offset") Integer offset,@Param("userId") Long userId,@Param("orderStatus") Integer orderStatus);

    List<Order> queryAll(@Param("size") Integer size,@Param("offset") Integer offset);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    boolean insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    boolean update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    boolean deleteById(Long orderId);

}

