package com.yuegou.service.impl;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.UserDao;
import com.yuegou.entity.Store;
import com.yuegou.dao.StoreDao;
import com.yuegou.entity.User;
import com.yuegou.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private UserDao userDao;

    public List<Store> queryAll(Integer size,Integer offset) {
        return storeDao.queryAll(size, offset);
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
        if (storeDao.queryByUserId(store.getUserId()) != null) throw new CURDException(Code.SAVE_ERR,"您已经有店铺了！");
        User user = userDao.getById(store.getUserId());
        user.setUserPower(2);
        if (!userDao.update(user))throw new CURDException(Code.UPDATE_ERR,"权限更新失败！");
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
        Store store = storeDao.queryById(id);
        User user = userDao.getById(store.getUserId());
        user.setUserPower(1);
        if (!userDao.update(user))throw new CURDException(Code.UPDATE_ERR,"权限更新失败！");
        return storeDao.deleteById(id);
    }

    public List<Store> queryStoreAndSpu() {
        return storeDao.queryStoreAndSpu();
    }

}
