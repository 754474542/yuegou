package com.yuegou.service;

import com.yuegou.entity.Spu;
import org.springframework.transaction.annotation.Transactional;

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
    Spu insert(Spu spu);

    /**
     * 通过主键删除数据
     *
     * @param spuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long spuId);

}
