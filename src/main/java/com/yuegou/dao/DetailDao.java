package com.yuegou.dao;

import com.yuegou.entity.Detail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Detail)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-13 10:03:34
 */
@Mapper
public interface DetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param detailId 主键
     * @return 实例对象
     */
    Detail queryById(Long detailId);

    List<Detail> queryByOrderId(Long orderId);

    List<Detail> queryByOrderIdMax(Long orderId);

    Detail queryBySkuIdAndSpuId(Detail detail);

    /**
     * 新增数据
     *
     * @param detail 实例对象
     * @return 影响行数
     */
    boolean insert(Detail detail);


    /**
     * 通过主键删除数据
     *
     * @param detailId 主键
     * @return 影响行数
     */
    boolean deleteById(Long detailId);

    boolean update(Detail detail);

}

