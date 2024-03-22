package com.yuegou.service;

import com.yuegou.entity.SpuImages;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpuImagesService {

    boolean insert(SpuImages spuImages);
    boolean updateByImageIdAndSpuId(SpuImages spuImages);
    boolean delete(Long imgId);
    SpuImages queryBySpuId(Long spuId);
    SpuImages queryByImgId(Long imgId);
}
