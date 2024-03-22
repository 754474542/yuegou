package com.yuegou.dao;

import com.yuegou.entity.Spu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Spu)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Mapper
public interface SpuDao {

    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 影响行数
     */
    boolean insert(Spu spu);


    /**
     * 修改数据
     *
     * @param spu 实例对象
     * @return 影响行数
     */
    boolean update(Spu spu);

    /**
     * 通过主键删除数据
     *
     * @param spuId 主键
     * @return 影响行数
     */
    boolean deleteById(Long spuId);

    List<Spu> queryAll(@Param("size") Integer size,@Param("offset") Integer offset);

    //根据店铺id查询
    List<Spu> queryByStoreId(Long storeId);

    List<Spu> queryByStoreIdMax(Long storeId);

    Spu queryBySpuId(Long spuId);

}

