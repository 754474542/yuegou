package com.yuegou.service;

import com.yuegou.entity.SkuAttributeValue;
import org.springframework.transaction.annotation.Transactional;

/**
 * (SkuAttributeValue)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Transactional
public interface SkuAttributeValueService {

    /**
     * 通过ID查询单条数据
     *
     * @param skuAttrId 主键
     * @return 实例对象
     */
    SkuAttributeValue queryById(Long skuAttrId);

    /**
     * 新增数据
     *
     * @param skuAttributeValue 实例对象
     * @return 实例对象
     */
    SkuAttributeValue insert(SkuAttributeValue skuAttributeValue);

    /**
     * 修改数据
     *
     * @param skuAttributeValue 实例对象
     * @return 实例对象
     */
    SkuAttributeValue update(SkuAttributeValue skuAttributeValue);

    /**
     * 通过主键删除数据
     *
     * @param skuAttrId 主键
     * @return 是否成功
     */
    boolean deleteById(Long skuAttrId);

}
