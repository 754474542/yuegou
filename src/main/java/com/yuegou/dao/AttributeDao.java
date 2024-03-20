package com.yuegou.dao;

import com.yuegou.entity.Attribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Attribute)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:32
 */
@Mapper
public interface AttributeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param attributeId 主键
     * @return 实例对象
     */
    Attribute queryById(Long attributeId);

    List<Attribute> queryByCateId(Long categoryId);


    /**
     * 统计总行数
     *
     * @param attribute 查询条件
     * @return 总行数
     */
    long count(Attribute attribute);

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 影响行数
     */
    boolean insert(Attribute attribute);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Attribute> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Attribute> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Attribute> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Attribute> entities);

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     * @return 影响行数
     */
    int update(Attribute attribute);

    /**
     * 通过主键删除数据
     *
     * @param attributeId 主键
     * @return 影响行数
     */
    int deleteById(Long attributeId);

}

