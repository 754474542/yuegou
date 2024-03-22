package com.yuegou.entity;

import java.util.List;

public class ImageDeleteEntity {
    private List<Long> imgIds;
    private Long skuId;

    public ImageDeleteEntity() {
    }

    @Override
    public String toString() {
        return "ImageDeleteEntity{" +
                "imgIds=" + imgIds +
                ", skuId=" + skuId +
                '}';
    }

    public List<Long> getImgIds() {
        return imgIds;
    }

    public void setImgIds(List<Long> imgIds) {
        this.imgIds = imgIds;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
}
