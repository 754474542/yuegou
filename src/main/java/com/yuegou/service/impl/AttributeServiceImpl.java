package com.yuegou.service.impl;

import com.yuegou.entity.Attribute;
import com.yuegou.dao.AttributeDao;
import com.yuegou.service.AttributeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * (Attribute)表服务实现类
 *
 * @author makejava
 * @since 2024-03-13 10:03:33
 */
@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {
    @Resource
    private AttributeDao attributeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param attributeId 主键
     * @return 实例对象
     */
    @Override
    public Attribute queryById(Long attributeId) {
        return this.attributeDao.queryById(attributeId);
    }

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    @Override
    public Attribute insert(Attribute attribute) {
        this.attributeDao.insert(attribute);
        return attribute;
    }

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    @Override
    public Attribute update(Attribute attribute) {
        this.attributeDao.update(attribute);
        return this.queryById(attribute.getAttributeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param attributeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long attributeId) {
        return this.attributeDao.deleteById(attributeId) > 0;
    }
}
