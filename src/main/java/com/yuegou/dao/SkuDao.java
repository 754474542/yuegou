package com.yuegou.dao;

import com.yuegou.entity.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Sku)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Mapper
public interface SkuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param skuId 主键
     * @return 实例对象
     */
    Sku queryById(Long skuId);

    List<Sku> queryBySpuId(Long spuId);


    /**
     * 统计总行数
     *
     * @param sku 查询条件
     * @return 总行数
     */
    long count(Sku sku);

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 影响行数
     */
    int insert(Sku sku);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Sku> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Sku> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Sku> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Sku> entities);

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 影响行数
     */
    int update(Sku sku);

    /**
     * 通过主键删除数据
     *
     * @param skuId 主键
     * @return 影响行数
     */
    int deleteById(Long skuId);

}

