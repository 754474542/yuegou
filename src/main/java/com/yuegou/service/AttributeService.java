package com.yuegou.service;

import com.yuegou.entity.Attribute;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Attribute)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:32
 */
@Transactional
public interface AttributeService {

    /**
     * 通过ID查询单条数据
     *
     * @param attributeId 主键
     * @return 实例对象
     */
    Attribute queryById(Long attributeId);

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    Attribute insert(Attribute attribute);

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    Attribute update(Attribute attribute);

    /**
     * 通过主键删除数据
     *
     * @param attributeId 主键
     * @return 是否成功
     */
    boolean deleteById(Long attributeId);

}
