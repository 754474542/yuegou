package com.yuegou.service;

import com.yuegou.entity.Store;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Store)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Transactional
public interface StoreService {

    List<Store> queryAll();

    /**
     * 通过ID查询单条数据
     *
     * @param storeId 主键
     * @return 实例对象
     */
    Store queryById(Long storeId);

    Store queryByUserId(Long userId);

    /**
     * 新增数据
     *
     * @param store 实例对象
     * @return 实例对象
     */
    boolean insert(Store store);

    /**
     * 修改数据
     *
     * @param store 实例对象
     * @return 实例对象
     */
    boolean update(Store store);

    boolean delete(Long id);

    List<Store> queryStoreAndSpu();

}
