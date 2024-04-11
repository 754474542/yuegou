package com.yuegou.entity;

public class SpuAttAndAttVal {

    private String attributeName;
    private String attributeValue;
    private Integer attributeType;

    @Override
    public String toString() {
        return "SpuAttAndAttVal{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                ", attributeType=" + attributeType +
                '}';
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Integer getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(Integer attributeType) {
        this.attributeType = attributeType;
    }
}
