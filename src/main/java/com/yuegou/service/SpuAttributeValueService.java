package com.yuegou.service;

import com.yuegou.entity.SpuAttributeValue;
import org.springframework.transaction.annotation.Transactional;

/**
 * (SpuAttributeValue)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Transactional
public interface SpuAttributeValueService {

    /**
     * 通过ID查询单条数据
     *
     * @param spuAttrId 主键
     * @return 实例对象
     */
    SpuAttributeValue queryById(Long spuAttrId);

    /**
     * 新增数据
     *
     * @param spuAttributeValue 实例对象
     * @return 实例对象
     */
    SpuAttributeValue insert(SpuAttributeValue spuAttributeValue);

    /**
     * 修改数据
     *
     * @param spuAttributeValue 实例对象
     * @return 实例对象
     */
    SpuAttributeValue update(SpuAttributeValue spuAttributeValue);

    /**
     * 通过主键删除数据
     *
     * @param spuAttrId 主键
     * @return 是否成功
     */
    boolean deleteById(Long spuAttrId);

}
