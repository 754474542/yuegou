package com.yuegou.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (Sku)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:09
 */
public class Sku implements Serializable {
    private static final long serialVersionUID = -98147681279055574L;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * spu_id
     */
    private Long spuId;
    /**
     * 商品价格
     */
    private Double skuPrice;
    /**
     * 库存
     */
    private String skuFund;

    private Spu spu;

    private List<SkuAttributeValue> skuAttributeValueList;

    private List<SkuImages> skuImagesList;

    private List<GoodEvaluate> goodEvaluateList;

    @Override
    public String toString() {
        return "Sku{" +
                "skuId=" + skuId +
                ", spuId=" + spuId +
                ", skuPrice=" + skuPrice +
                ", skuFund='" + skuFund + '\'' +
                ", spu=" + spu +
                ", skuAttributeValueList=" + skuAttributeValueList +
                ", skuImagesList=" + skuImagesList +
                ", goodEvaluateList=" + goodEvaluateList +
                '}';
    }

    public List<GoodEvaluate> getGoodEvaluateList() {
        return goodEvaluateList;
    }

    public void setGoodEvaluateList(List<GoodEvaluate> goodEvaluateList) {
        this.goodEvaluateList = goodEvaluateList;
    }

    public List<SkuImages> getSkuImagesList() {
        return skuImagesList;
    }

    public void setSkuImagesList(List<SkuImages> skuImagesList) {
        this.skuImagesList = skuImagesList;
    }

    public List<SkuAttributeValue> getSkuAttributeValueList() {
        return skuAttributeValueList;
    }

    public void setSkuAttributeValueList(List<SkuAttributeValue> skuAttributeValueList) {
        this.skuAttributeValueList = skuAttributeValueList;
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Double getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Double skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuFund() {
        return skuFund;
    }

    public void setSkuFund(String skuFund) {
        this.skuFund = skuFund;
    }

}

