package com.yuegou.service;

import com.yuegou.entity.SkuImages;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * (SkuImages)表服务接口
 *
 * @author makejava
 * @since 2024-03-14 13:16:51
 */
@Transactional
public interface SkuImagesService {

    SkuImages queryById(Long imgId);
    SkuImages queryByImgIdAndSkuId(SkuImages skuImages);
    List<SkuImages> queryBySkuId(Long skuId);
    List<SkuImages> queryAll();
    boolean updateByImageIdAndSkuId(SkuImages skuImages);
    boolean save(SkuImages skuImages);
    boolean delete(Long imgId);

}
