package com.yuegou.service.impl;

import com.yuegou.entity.Sku;
import com.yuegou.dao.SkuDao;
import com.yuegou.service.SkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Sku)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Resource
    private SkuDao skuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param skuId 主键
     * @return 实例对象
     */
    @Override
    public Sku queryById(Long skuId) {
        return this.skuDao.queryById(skuId);
    }

    @Override
    public List<Sku> queryBySpuId(Long spuId) {
        return skuDao.queryBySpuId(spuId);
    }

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    @Override
    public Sku insert(Sku sku) {
        this.skuDao.insert(sku);
        return sku;
    }

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    @Override
    public Sku update(Sku sku) {
        this.skuDao.update(sku);
        return this.queryById(sku.getSkuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param skuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long skuId) {
        return this.skuDao.deleteById(skuId) > 0;
    }
}
