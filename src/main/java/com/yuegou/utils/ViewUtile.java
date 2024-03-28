package com.yuegou.utils;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.CURDException;
import com.yuegou.dao.SpuImagesDao;
import com.yuegou.entity.SpuImages;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ViewUtile {

    @Autowired
    private SpuImagesDao spuImagesDao;
    @Autowired
    private Logger logger;

    //获取打乱顺序的轮播图
    public List<SpuImages> getSpuImages(){
        List<SpuImages> bannerList = new ArrayList<>();
        List<SpuImages> spuImages = spuImagesDao.queryBanner();
        Collections.shuffle(spuImages);
        if (spuImages.size() < 8 ){
            logger.error("可用轮播图数量小于8张");
            return bannerList;
        }
        for (int i = 0; i < 8; i++) {
            bannerList.add(spuImages.get(i));
        }
        return bannerList;
    }

}
