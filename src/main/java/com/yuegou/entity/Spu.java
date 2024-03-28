package com.yuegou.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Spu)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:10
 */
public class Spu implements Serializable {
    private static final long serialVersionUID = -68053433726189250L;
    /**
     * spu商品id
     */
    private Long spuId;
    
    private Long storeId;
    /**
     * 商品名称
     */
    private String spuName;
    /**
     * 标题
     */
    private String spuTitle;
    /**
     * 描述
     */
    private String spuDescription;
    /**
     * 类别
     */
    private Long categoryId;
    /**
     * 状态0 下架 1 上架 2 删除
     */
    private Integer spuStatus;
    /**
     * 创建时间
     */
    private Date createTime;

    private Integer discount;

    private Category category;

    private List<Sku> skuList;

    private List<Attribute> attributeList;

    private List<SpuAttributeValue> spuAttributeValueList;

    private SpuImages spuImages;

    private String indexSpuPrice;

    @Override
    public String toString() {
        return "Spu{" +
                "spuId=" + spuId +
                ", storeId=" + storeId +
                ", spuName='" + spuName + '\'' +
                ", spuTitle='" + spuTitle + '\'' +
                ", spuDescription='" + spuDescription + '\'' +
                ", categoryId=" + categoryId +
                ", spuStatus=" + spuStatus +
                ", createTime=" + createTime +
                ", discount=" + discount +
                ", category=" + category +
                ", skuList=" + skuList +
                ", attributeList=" + attributeList +
                ", spuAttributeValueList=" + spuAttributeValueList +
                ", spuImages=" + spuImages +
                '}';
    }

    public String getIndexSpuPrice() {
        return indexSpuPrice;
    }

    public void setIndexSpuPrice(String indexSpuPrice) {
        this.indexSpuPrice = indexSpuPrice;
    }

    public SpuImages getSpuImages() {
        return spuImages;
    }

    public void setSpuImages(SpuImages spuImages) {
        this.spuImages = spuImages;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<SpuAttributeValue> getSpuAttributeValueList() {
        return spuAttributeValueList;
    }

    public void setSpuAttributeValueList(List<SpuAttributeValue> spuAttributeValueList) {
        this.spuAttributeValueList = spuAttributeValueList;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuTitle() {
        return spuTitle;
    }

    public void setSpuTitle(String spuTitle) {
        this.spuTitle = spuTitle;
    }

    public String getSpuDescription() {
        return spuDescription;
    }

    public void setSpuDescription(String spuDescription) {
        this.spuDescription = spuDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSpuStatus() {
        return spuStatus;
    }

    public void setSpuStatus(Integer spuStatus) {
        this.spuStatus = spuStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

