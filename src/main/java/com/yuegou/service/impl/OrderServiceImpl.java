package com.yuegou.service.impl;

import com.yuegou.entity.Order;
import com.yuegou.dao.OrderDao;
import com.yuegou.service.OrderService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Long orderId) {
        return this.orderDao.queryById(orderId);
    }

    @Override
    public List<Order> queryByUserId(Long userId) {
        return orderDao.queryByUserId(userId);
    }

    @Override
    public List<Order> queryAll() {
        return orderDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Order order) {
        order.setCreateTime(new Date());
        return orderDao.insert(order);
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Order order) {
        return orderDao.update(order);
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long orderId) {
        return this.orderDao.deleteById(orderId) > 0;
    }
}
