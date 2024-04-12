package com.yuegou.dao;

import com.yuegou.entity.GoodEvaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GoodEvaluate)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Mapper
public interface GoodEvaluateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param evaluateId 主键
     * @return 实例对象
     */
    GoodEvaluate queryById(Long evaluateId);

    List<GoodEvaluate> queryBySkuId(Long skuId);

    List<GoodEvaluate> queryAll(@Param("size") Integer size,@Param("offset") Integer offset);

    List<GoodEvaluate> queryByUserId(Long userId);

    List<GoodEvaluate> queryBySpuId(Long spuId);

    List<GoodEvaluate> queryBySpuIdLimit(@Param("size") Integer size,@Param("offset") Integer offset,@Param("spuId") Long spuId);

    /**
     * 新增数据
     *
     * @param goodEvaluate 实例对象
     * @return 影响行数
     */
    boolean insert(GoodEvaluate goodEvaluate);

    /**
     * 修改数据
     *
     * @param goodEvaluate 实例对象
     * @return 影响行数
     */
    boolean update(GoodEvaluate goodEvaluate);

    /**
     * 通过主键删除数据
     *
     * @param evaluateId 主键
     * @return 影响行数
     */
    boolean deleteById(Long evaluateId);

    boolean deleteBySkuId(Long skuId);

}

