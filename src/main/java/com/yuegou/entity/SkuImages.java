package com.yuegou.entity;

import java.io.Serializable;

/**
 * (SkuImages)实体类
 *
 * @author makejava
 * @since 2024-03-14 13:15:41
 */
public class SkuImages implements Serializable {
    private static final long serialVersionUID = -65086235395834238L;

    private Long imgId;

    private Long skuId;

    private String imgPath;

    public SkuImages() {
    }

    @Override
    public String toString() {
        return "SkuImages{" +
                "imgId=" + imgId +
                ", skuId=" + skuId +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public SkuImages(Long imgId, Long skuId, String imgPath) {
        this.imgId = imgId;
        this.skuId = skuId;
        this.imgPath = imgPath;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}

