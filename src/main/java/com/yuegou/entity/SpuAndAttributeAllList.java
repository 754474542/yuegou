package com.yuegou.entity;

import java.util.List;

public class SpuAndAttributeAllList {

    private Spu spu;

    private List<SpuAttAndAttVal> spuAttAndAttValList;

    @Override
    public String toString() {
        return "SpuAndAttributeAllList{" +
                "spu=" + spu +
                ", spuAttAndAttValList=" + spuAttAndAttValList +
                '}';
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<SpuAttAndAttVal> getSpuAttAndAttValList() {
        return spuAttAndAttValList;
    }

    public void setSpuAttAndAttValList(List<SpuAttAndAttVal> spuAttAndAttValList) {
        this.spuAttAndAttValList = spuAttAndAttValList;
    }
}
