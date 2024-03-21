package com.yuegou.entity;

import java.util.List;

public class SkuAndAttributeValues {
    private Sku sku;
    private List<SkuAttributeValue> skuAttributeValues;

    @Override
    public String toString() {
        return "SkuAndAttributeValues{" +
                "sku=" + sku +
                ", skuAttributeValues=" + skuAttributeValues +
                '}';
    }

    public SkuAndAttributeValues() {
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public List<SkuAttributeValue> getSkuAttributeValues() {
        return skuAttributeValues;
    }

    public void setSkuAttributeValues(List<SkuAttributeValue> skuAttributeValues) {
        this.skuAttributeValues = skuAttributeValues;
    }
}
