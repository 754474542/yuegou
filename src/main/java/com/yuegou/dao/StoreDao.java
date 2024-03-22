package com.yuegou.dao;

import com.yuegou.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Store)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:36
 */
@Mapper
public interface StoreDao {

    List<Store> queryAll(@Param("size") Integer size,@Param("offset") Integer offset);

    /**
     * 通过ID查询单条数据
     *
     * @param storeId 主键
     * @return 实例对象
     */
    Store queryById(Long storeId);

    Store queryByUserId(Long userId);

    /**
     * 新增数据
     *
     * @param store 实例对象
     * @return 影响行数
     */
    boolean insert(Store store);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Store> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Store> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Store> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时   候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Store> entities);

    /**
     * 修改数据
     *
     * @param store 实例对象
     * @return 影响行数
     */
    boolean update(Store store);

    /**
     * 通过主键删除数据
     *
     * @param storeId 主键
     * @return 影响行数
     */
    boolean deleteById(Long storeId);

    List<Store> queryStoreAndSpu();

}

