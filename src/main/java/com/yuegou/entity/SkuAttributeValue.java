package com.yuegou.entity;

import java.io.Serializable;

/**
 * (SkuAttributeValue)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:10
 */
public class SkuAttributeValue implements Serializable {
    private static final long serialVersionUID = 817526131279274982L;
    /**
     * sku属性id
     */
    private Long skuAttrId;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * 属性id
     */
    private Long attributeId;
    /**
     * 属性值
     */
    private String attributeValue;


    public Long getSkuAttrId() {
        return skuAttrId;
    }

    public void setSkuAttrId(Long skuAttrId) {
        this.skuAttrId = skuAttrId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public String toString() {
        return "SkuAttributeValue{" +
                "skuAttrId=" + skuAttrId +
                ", skuId=" + skuId +
                ", attributeId=" + attributeId +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}

