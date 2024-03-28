package com.yuegou.service;

import com.yuegou.entity.SpuImages;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpuImagesService {

    boolean insert(SpuImages spuImages);
    boolean updateBannerByImageIdAndSkuId(SpuImages spuImages);
    boolean updateBySpuImgImageIdAndSpuId(SpuImages spuImages);
    boolean delete(Long imgId);
    SpuImages queryBySpuId(Long spuId);
    SpuImages queryByImgId(Long imgId);
    List<SpuImages> queryAll();
}
