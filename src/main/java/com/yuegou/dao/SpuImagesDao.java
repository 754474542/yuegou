package com.yuegou.dao;

import com.yuegou.entity.SpuImages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpuImagesDao {

    boolean insert(SpuImages spuImages);
    boolean updateByImageIdAndSpuId(SpuImages spuImages);
    boolean delete(Long imgId);
    SpuImages queryBySpuId(Long spuId);
    SpuImages queryByImgId(Long imgId);
    List<SpuImages> queryAll();

}
