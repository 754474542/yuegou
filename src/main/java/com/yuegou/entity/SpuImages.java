package com.yuegou.entity;

public class SpuImages {

    private Long imgId;
    private Long spuId;
    private String imgPath;
    private String indexImgPath;
    private String imgPathBase64;
    private String indexImgPathBase64;

    public SpuImages() {
    }

    public SpuImages(Long imgId, Long spuId, String imgPath) {
        this.imgId = imgId;
        this.spuId = spuId;
        this.imgPath = imgPath;
    }

    public SpuImages(Long imgId, Long spuId, String imgPath, String indexImgPath) {
        this.imgId = imgId;
        this.spuId = spuId;
        this.imgPath = imgPath;
        this.indexImgPath = indexImgPath;
    }

    public String getImgPathBase64() {
        return imgPathBase64;
    }

    public void setImgPathBase64(String imgPathBase64) {
        this.imgPathBase64 = imgPathBase64;
    }

    public String getIndexImgPathBase64() {
        return indexImgPathBase64;
    }

    public void setIndexImgPathBase64(String indexImgPathBase64) {
        this.indexImgPathBase64 = indexImgPathBase64;
    }

    @Override
    public String toString() {
        return "SpuImages{" +
                "imgId=" + imgId +
                ", spuId=" + spuId +
                ", imgPath='" + imgPath + '\'' +
                ", indexImgPath='" + indexImgPath + '\'' +
                ", imgPathBase64='" + imgPathBase64 + '\'' +
                ", indexImgPathBase64='" + indexImgPathBase64 + '\'' +
                '}';
    }

    public String getIndexImgPath() {
        return indexImgPath;
    }

    public void setIndexImgPath(String indexImgPath) {
        this.indexImgPath = indexImgPath;
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
