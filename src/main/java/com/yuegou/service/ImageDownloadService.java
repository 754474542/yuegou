package com.yuegou.service;

import com.yuegou.entity.ImageDeleteEntity;
import com.yuegou.entity.QainImageEntity;
import com.yuegou.entity.SpuImages;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
public interface ImageDownloadService {

    boolean userHeadFileUp(MultipartFile file, String token);
    boolean storeImgFileUp(List<MultipartFile> files,List<Long> imgIds,Long skuId,String token);
    boolean storeImgDelete(ImageDeleteEntity imageDeleteEntity, String token);
    boolean spuImgFileUp(MultipartFile file,Long spuId,String token);
    boolean spuImgFileDelete(Long imgId, String token);
    String queryOneImage(QainImageEntity imagePath);
    List<SpuImages> queryBannerList();

}
