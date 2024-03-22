package com.yuegou.dao;

import com.yuegou.entity.SkuAttributeValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SkuAttributeValue)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:35
 */
@Mapper
public interface SkuAttributeValueDao {

    /**
     * 通过ID查询单条数据
     *
     * @param skuAttrId 主键
     * @return 实例对象
     */
    SkuAttributeValue queryById(Long skuAttrId);

    List<SkuAttributeValue> queryBySkuId(Long skuAttrId);

    /**
     * 统计总行数
     *
     * @param skuAttributeValue 查询条件
     * @return 总行数
     */
    long count(SkuAttributeValue skuAttributeValue);

    /**
     * 新增数据
     *
     * @param skuAttributeValue 实例对象
     * @return 影响行数
     */
    boolean insert(SkuAttributeValue skuAttributeValue);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SkuAttributeValue> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SkuAttributeValue> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SkuAttributeValue> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SkuAttributeValue> entities);

    /**
     * 修改数据
     *
     * @param skuAttributeValue 实例对象
     * @return 影响行数
     */
    boolean update(SkuAttributeValue skuAttributeValue);

    /**
     * 通过主键删除数据
     *
     * @param skuAttrId 主键
     * @return 影响行数
     */
    boolean deleteById(Long skuAttrId);

    boolean deleteBySkuId(Long skuId);

}

