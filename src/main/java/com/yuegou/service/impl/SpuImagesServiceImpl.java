package com.yuegou.service.impl;

import com.yuegou.dao.SpuImagesDao;
import com.yuegou.entity.SpuImages;
import com.yuegou.service.SpuImagesService;
import com.yuegou.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class SpuImagesServiceImpl implements SpuImagesService {

    @Autowired
    private SpuImagesDao spuImagesDao;
    @Value("${utils.imagessavepath}")
    private String path;

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
        SpuImages spuImages = spuImagesDao.queryByImgId(imgId);
        if (spuImages.getImgPath() != null){
            spuImages.setImgPathBase64(FileUtil.fileToByte(path + spuImages.getImgPath()));
        }
        if (spuImages.getIndexImgPath() != null){
            spuImages.setIndexImgPathBase64(FileUtil.fileToByte(path + spuImages.getIndexImgPath()));
        }
        return spuImages;
    }

    @Override
    public List<SpuImages> queryAll() {
        return spuImagesDao.queryAll();
    }
}
