package com.yuegou.dao;

import com.yuegou.entity.SpuImages;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SpuImagesDao {

    boolean insert(SpuImages spuImages);
    boolean updateByImageIdAndSpuId(SpuImages spuImages);
    boolean delete(Long imgId);
    SpuImages queryBySpuId(Long spuId);
    SpuImages queryByImgId(Long imgId);

}
