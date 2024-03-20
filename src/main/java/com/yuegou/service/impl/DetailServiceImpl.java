package com.yuegou.service.impl;

import com.yuegou.entity.Detail;
import com.yuegou.dao.DetailDao;
import com.yuegou.service.DetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Detail)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Service("detailService")
public class DetailServiceImpl implements DetailService {
    @Resource
    private DetailDao detailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param detailId 主键
     * @return 实例对象
     */
    @Override
    public Detail queryById(Long detailId) {
        return detailDao.queryById(detailId);
    }

    @Override
    public List<Detail> queryByOrderId(Long orderId) {
        return detailDao.queryByOrderId(orderId);
    }


    /**
     * 新增数据
     *
     * @param detail 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Detail detail) {
        return detailDao.insert(detail);
    }

    /**
     * 通过主键删除数据
     *
     * @param detailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long detailId) {
        return detailDao.deleteById(detailId);
    }

    @Override
    public boolean update(Detail detail) {
        //去根据detail ID去查询里面的东西，然后在计算spu sku直间的价格。
        System.out.println(detail);
        return detailDao.update(detail);
    }
}
