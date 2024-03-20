package com.yuegou.service.impl;

import com.yuegou.entity.SkuImages;
import com.yuegou.dao.SkuImagesDao;
import com.yuegou.service.SkuImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SkuImages)表服务实现类
 *
 * @author makejava
 * @since 2024-03-14 13:16:51
 */
@Service("skuImagesService")
public class SkuImagesServiceImpl implements SkuImagesService {
    @Autowired
    private SkuImagesDao skuImagesDao;


    @Override
    public SkuImages queryById(Long imgId) {
        return skuImagesDao.queryById(imgId);
    }

    @Override
    public SkuImages queryByImgIdAndSkuId(SkuImages skuImages) {
        return skuImagesDao.queryByImgIdAndSkuId(skuImages);
    }

    @Override
    public List<SkuImages> queryBySkuId(Long skuId) {
        return skuImagesDao.queryBySkuId(skuId);
    }

    @Override
    public List<SkuImages> queryAll() {
        return skuImagesDao.queryAll();
    }

    @Override
    public boolean updateByImageIdAndSkuId(SkuImages skuImages) {
        return skuImagesDao.updateByImageIdAndSkuId(skuImages);
    }

    @Override
    public boolean save(SkuImages skuImages) {
        return skuImagesDao.save(skuImages);
    }

    @Override
    public boolean delete(Long imgId) {
        return skuImagesDao.delete(imgId);
    }
}
