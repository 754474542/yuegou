package com.yuegou.service;

import com.yuegou.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Transactional
public interface OrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    Order queryById(Long orderId);

    List<Order> queryByUserId(Long userId);

    List<Order> queryAll();

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    boolean insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    boolean update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(Long orderId);

}
