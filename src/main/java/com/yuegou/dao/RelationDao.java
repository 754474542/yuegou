package com.yuegou.dao;

import com.yuegou.entity.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Relation)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-14 09:41:37
 */
@Mapper
public interface RelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param relationId 主键
     * @return 实例对象
     */
    Relation queryById(Long relationId);

    Relation queryBySpuId(Long spuId);

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
    boolean insert(Relation relation);

    /**
     * 修改数据
     *
     * @param relation 实例对象
     * @return 影响行数
     */
    boolean update(Relation relation);

    /**
     * 通过主键删除数据
     *
     * @param relationId 主键
     * @return 影响行数
     */
    boolean deleteById(Long relationId);


}

