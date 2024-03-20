package com.yuegou.entity;

import java.util.List;

public class SpuAndAttributeValues {
    private Spu spu;
    private List<SpuAttributeValue> spuAttributeValueList;

    @Override
    public String toString() {
        return "SpuAndAttributeValues{" +
                "spu=" + spu +
                ", spuAttributeValueList=" + spuAttributeValueList +
                '}';
    }

    public SpuAndAttributeValues(Spu spu, List<SpuAttributeValue> spuAttributeValueList) {
        this.spu = spu;
        this.spuAttributeValueList = spuAttributeValueList;
    }

    public SpuAndAttributeValues() {
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<SpuAttributeValue> getSpuAttributeValueList() {
        return spuAttributeValueList;
    }

    public void setSpuAttributeValueList(List<SpuAttributeValue> spuAttributeValueList) {
        this.spuAttributeValueList = spuAttributeValueList;
    }
}
