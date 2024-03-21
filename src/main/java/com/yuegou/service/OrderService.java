package com.yuegou.service;

import com.yuegou.entity.Order;
import com.yuegou.entity.OrderAndDetail;
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

    boolean insert(OrderAndDetail orderAndDetail);

    boolean update(OrderAndDetail orderAndDetail);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(Long orderId);

    double calPrice(Long skuId,Integer number,Long detailId);

}
