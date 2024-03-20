package com.yuegou.service.impl;

import com.yuegou.entity.Store;
import com.yuegou.dao.StoreDao;
import com.yuegou.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Store)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreDao storeDao;

    public List<Store> queryAll() {
        return storeDao.queryAll();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param storeId 主键
     * @return 实例对象
     */
    @Override
    public Store queryById(Long storeId) {
        return storeDao.queryById(storeId);
    }

    @Override
    public Store queryByUserId(Long userId) {
        return storeDao.queryByUserId(userId);
    }

    /**
     * 新增数据
     *
     * @param store 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Store store) {
        return storeDao.insert(store);
    }

    /**
     * 修改数据
     *
     * @param store 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Store store) {
        return storeDao.update(store);
    }

    @Override
    public boolean delete(Long id) {
        return storeDao.deleteById(id);
    }

    public List<Store> queryStoreAndSpu() {
        return storeDao.queryStoreAndSpu();
    }

}
