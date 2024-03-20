package com.yuegou.entity;

import java.io.Serializable;

/**
 * (SpuAttributeValue)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:10
 */
public class SpuAttributeValue implements Serializable {
    private static final long serialVersionUID = 297711240528570580L;
    /**
     * 商品属性id
     */
    private Long spuAttrId;
    /**
     * sup_id
     */
    private Long spuId;
    /**
     * 属性id
     */
    private Long attributeId;
    /**
     * 属性值
     */
    private String attributeValue;


    public Long getSpuAttrId() {
        return spuAttrId;
    }

    public void setSpuAttrId(Long spuAttrId) {
        this.spuAttrId = spuAttrId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
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
        return "SpuAttributeValue{" +
                "spuAttrId=" + spuAttrId +
                ", spuId=" + spuId +
                ", attributeId=" + attributeId +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}

