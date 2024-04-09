package com.yuegou.dao;

import com.yuegou.entity.Spu;
import com.yuegou.entity.SpuSearchEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
    //根据店铺id查询，支持分页
    List<Spu> queryByStoreIdAllSpu(@Param("size") Integer size,@Param("offset") Integer offset,@Param("storeId") Long storeId);

    //根据店铺id查询
    List<Spu> queryByStoreId(Long storeId);

    List<Spu> queryByStoreIdMax(Long storeId);

    List<Spu> queryIndexPageList(@Param("size") Integer size,@Param("offset") Integer offset);

    Spu queryBySpuId(Long spuId);

    List<Spu> querySearchSpu(SpuSearchEntity spuSearchEntity);


}

