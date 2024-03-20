package com.yuegou.dao;


import com.yuegou.entity.Sku;
import com.yuegou.entity.SkuImages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (SkuImages)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-14 13:16:51
 */
@Mapper
public interface SkuImagesDao {

    SkuImages queryById(Long imgId);
    SkuImages queryByImgIdAndSkuId(SkuImages skuImages);
    List<SkuImages> queryBySkuId(Long skuId);
    List<SkuImages> queryAll();
    boolean updateByImageIdAndSkuId(SkuImages skuImages);
    boolean save(SkuImages skuImages);
    boolean delete(Long imgId);

}

