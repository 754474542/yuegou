package com.yuegou.dao;

import com.yuegou.entity.Relation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Relation)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-14 09:41:37
 */
public interface RelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param relationId 主键
     * @return 实例对象
     */
    Relation queryById(Long relationId);

    List<Relation> queryByStoreId(Long storeId);

    /**
     * 统计总行数
     *
     * @param relation 查询条件
     * @return 总行数
     */
    long count(Relation relation);

    /**
     * 新增数据
     *
     * @param relation 实例对象
     * @return 影响行数
     */
    int insert(Relation relation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Relation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Relation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Relation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Relation> entities);

    /**
     * 修改数据
     *
     * @param relation 实例对象
     * @return 影响行数
     */
    int update(Relation relation);

    /**
     * 通过主键删除数据
     *
     * @param relationId 主键
     * @return 影响行数
     */
    int deleteById(Long relationId);

}

