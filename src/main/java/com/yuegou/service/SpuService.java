package com.yuegou.service;

import com.yuegou.entity.Spu;
import com.yuegou.entity.SpuAndAttributeAllList;
import com.yuegou.entity.SpuAndAttributeValues;
import com.yuegou.entity.SpuSearchEntity;
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

    boolean update(Spu spu);

    List<Spu> queryAll(Integer size, Integer offset);

    Spu queryBySpuId(Long spuId);

    boolean saveSpuAndAttributeValues(SpuAndAttributeValues spuAndAttributeValues,String token);

    boolean intersectSpuAndAttribute(SpuAndAttributeAllList spuAndAttributeAllList);

    List<Spu> queryIndexPageList(Integer size, Integer offset);

    List<Spu> querySearchSpu(SpuSearchEntity spuSearchEntity);

    List<Spu> queryByStoreId(Integer size, Integer offset, Long storeId,String search,Integer power);

}
