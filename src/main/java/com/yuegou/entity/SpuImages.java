package com.yuegou.entity;

public class SpuImages {

    private Long imgId;
    private Long spuId;
    private String imgPath;

    public SpuImages() {
    }

    public SpuImages(Long imgId, Long spuId, String imgPath) {
        this.imgId = imgId;
        this.spuId = spuId;
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "SpuImages{" +
                "imgId=" + imgId +
                ", spuId=" + spuId +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
