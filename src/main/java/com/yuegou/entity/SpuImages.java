package com.yuegou.entity;

public class SpuImages {

    private Long imgId;
    private Long spuId;
    private String imgPath;
    private String base64;

    public SpuImages() {
    }

    public SpuImages(Long imgId, Long spuId, String imgPath) {
        this.imgId = imgId;
        this.spuId = spuId;
        this.imgPath = imgPath;
    }

    public SpuImages(Long imgId, Long spuId, String imgPath, String base64) {
        this.imgId = imgId;
        this.spuId = spuId;
        this.imgPath = imgPath;
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "SpuImages{" +
                "imgId=" + imgId +
                ", spuId=" + spuId +
                ", imgPath='" + imgPath + '\'' +
                ", base64='" + base64 + '\'' +
                '}';
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
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
