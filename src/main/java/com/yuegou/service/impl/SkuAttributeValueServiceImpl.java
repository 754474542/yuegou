package com.yuegou.service.impl;

import com.yuegou.entity.SkuAttributeValue;
import com.yuegou.dao.SkuAttributeValueDao;
import com.yuegou.service.SkuAttributeValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (SkuAttributeValue)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Service("skuAttributeValueService")
public class SkuAttributeValueServiceImpl implements SkuAttributeValueService {
    @Resource
    private SkuAttributeValueDao skuAttributeValueDao;

    /**
     * 通过ID查询单条数据
     *
     * @param skuAttrId 主键
     * @return 实例对象
     */
    @Override
    public SkuAttributeValue queryById(Long skuAttrId) {
        return this.skuAttributeValueDao.queryById(skuAttrId);
    }

    /**
     * 新增数据
     *
     * @param skuAttributeValue 实例对象
     * @return 实例对象
     */
    @Override
    public SkuAttributeValue insert(SkuAttributeValue skuAttributeValue) {
        this.skuAttributeValueDao.insert(skuAttributeValue);
        return skuAttributeValue;
    }

    /**
     * 修改数据
     *
     * @param skuAttributeValue 实例对象
     * @return 实例对象
     */
    @Override
    public SkuAttributeValue update(SkuAttributeValue skuAttributeValue) {
        this.skuAttributeValueDao.update(skuAttributeValue);
        return this.queryById(skuAttributeValue.getSkuAttrId());
    }

    /**
     * 通过主键删除数据
     *
     * @param skuAttrId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long skuAttrId) {
        return this.skuAttributeValueDao.deleteById(skuAttrId) > 0;
    }
}
