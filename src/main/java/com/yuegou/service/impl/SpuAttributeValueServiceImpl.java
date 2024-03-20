package com.yuegou.service.impl;

import com.yuegou.entity.SpuAttributeValue;
import com.yuegou.dao.SpuAttributeValueDao;
import com.yuegou.service.SpuAttributeValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (SpuAttributeValue)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Service("spuAttributeValueService")
public class SpuAttributeValueServiceImpl implements SpuAttributeValueService {
    @Resource
    private SpuAttributeValueDao spuAttributeValueDao;

    /**
     * 通过ID查询单条数据
     *
     * @param spuAttrId 主键
     * @return 实例对象
     */
    @Override
    public SpuAttributeValue queryById(Long spuAttrId) {
        return this.spuAttributeValueDao.queryById(spuAttrId);
    }

    /**
     * 新增数据
     *
     * @param spuAttributeValue 实例对象
     * @return 实例对象
     */
    @Override
    public SpuAttributeValue insert(SpuAttributeValue spuAttributeValue) {
        this.spuAttributeValueDao.insert(spuAttributeValue);
        return spuAttributeValue;
    }

    /**
     * 修改数据
     *
     * @param spuAttributeValue 实例对象
     * @return 实例对象
     */
    @Override
    public SpuAttributeValue update(SpuAttributeValue spuAttributeValue) {
        this.spuAttributeValueDao.update(spuAttributeValue);
        return this.queryById(spuAttributeValue.getSpuAttrId());
    }

    /**
     * 通过主键删除数据
     *
     * @param spuAttrId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long spuAttrId) {
        return this.spuAttributeValueDao.deleteById(spuAttrId) > 0;
    }
}
