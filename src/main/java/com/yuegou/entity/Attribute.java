package com.yuegou.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Attribute)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:09
 */
public class Attribute implements Serializable {
    private static final long serialVersionUID = 232412927973990890L;

    private Long attributeId;

    private String attributeName;

    private String attributeOptions;

    private Integer attributeType;

    private Long attributeSort;

    private Long categoryId;

    private Date createTime;

    private Long spuId;

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

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeOptions() {
        return attributeOptions;
    }

    public void setAttributeOptions(String attributeOptions) {
        this.attributeOptions = attributeOptions;
    }

    public Integer getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(Integer attributeType) {
        this.attributeType = attributeType;
    }

    public Long getAttributeSort() {
        return attributeSort;
    }

    public void setAttributeSort(Long attributeSort) {
        this.attributeSort = attributeSort;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "attributeId=" + attributeId +
                ", attributeName='" + attributeName + '\'' +
                ", attributeOptions='" + attributeOptions + '\'' +
                ", attributeType=" + attributeType +
                ", attributeSort=" + attributeSort +
                ", categoryId=" + categoryId +
                ", createTime=" + createTime +
                ", spuId=" + spuId +
                '}';
    }
}

