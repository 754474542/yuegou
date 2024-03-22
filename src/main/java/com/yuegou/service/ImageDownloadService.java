package com.yuegou.service;

import com.yuegou.entity.ImageDeleteEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
public interface ImageDownloadService {

    boolean userHeadFileUp(MultipartFile file, String token);
    boolean storeImgFileUp(List<MultipartFile> files,List<Long> imgIds,Long skuId,String token);
    boolean storeImgDelete(ImageDeleteEntity imageDeleteEntity, String token);
    boolean spuImgFileUp(MultipartFile file,Long spuId,String token);
    boolean spuImgFileDelete(Long imgId, String token);

}
