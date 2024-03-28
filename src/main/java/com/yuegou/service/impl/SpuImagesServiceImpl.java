package com.yuegou.service.impl;

import com.yuegou.dao.SpuImagesDao;
import com.yuegou.entity.SpuImages;
import com.yuegou.service.SpuImagesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SpuImagesServiceImpl implements SpuImagesService {

    @Autowired
    private SpuImagesDao spuImagesDao;

    @Override
    public boolean insert(SpuImages spuImages) {
        return spuImagesDao.insert(spuImages);
    }

    @Override
    public boolean updateBySpuImgImageIdAndSpuId(SpuImages spuImages) {
        return spuImagesDao.updateSpuImgByImageIdAndSkuId(spuImages);
    }

    @Override
    public boolean updateBannerByImageIdAndSkuId(SpuImages spuImages) {
        return spuImagesDao.updateBannerByImageIdAndSkuId(spuImages);
    }

    @Override
    public boolean delete(Long imgId) {
        return spuImagesDao.delete(imgId);
    }

    @Override
    public SpuImages queryBySpuId(Long spuId) {
        return spuImagesDao.queryBySpuId(spuId);
    }

    @Override
    public SpuImages queryByImgId(Long imgId) {
        return spuImagesDao.queryByImgId(imgId);
    }

    @Override
    public List<SpuImages> queryAll() {
        return spuImagesDao.queryAll();
    }
}
