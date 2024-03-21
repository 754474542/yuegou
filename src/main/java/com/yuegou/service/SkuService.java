package com.yuegou.service;

import com.yuegou.entity.Sku;
import com.yuegou.entity.SkuAndAttributeValues;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Sku)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Transactional
public interface SkuService {

    /**
     * 通过ID查询单条数据
     *
     * @param skuId 主键
     * @return 实例对象
     */
    Sku queryById(Long skuId);

    List<Sku> queryBySpuId(Long spuId);

    List<Sku> queryAll();

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    boolean insert(Sku sku);

    boolean insertSkuAndAttributeValues (SkuAndAttributeValues skuAndAttributeValues);

    boolean update(SkuAndAttributeValues skuAndAttributeValues);

    /**
     * 通过主键删除数据
     *
     * @param skuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long skuId);

}
