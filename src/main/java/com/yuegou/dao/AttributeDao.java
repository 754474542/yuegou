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

    List<Attribute> queryBySpuId(Long spuId);

    boolean insert(Attribute attribute);

    boolean update(Attribute attribute);

    boolean deleteBySpuId(Long spuId);

}

