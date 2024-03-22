package com.yuegou.service;

import com.yuegou.entity.Spu;
import com.yuegou.entity.SpuAndAttributeValues;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Spu)表服务接口
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Transactional
public interface SpuService {

    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    boolean insert(Spu spu);

    /**
     * 通过主键删除数据
     *
     * @param spuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long spuId);

    boolean update(SpuAndAttributeValues spuAndAttributeValues);

    List<Spu> queryAll(Integer size, Integer offset);

    Spu queryBySpuId(Long spuId);

    boolean saveSpuAndAttributeValues(SpuAndAttributeValues spuAndAttributeValues,String token);

}
