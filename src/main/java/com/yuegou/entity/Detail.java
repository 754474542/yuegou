package com.yuegou.entity;

import java.io.Serializable;

/**
 * (Detail)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:09
 */
public class Detail implements Serializable {
    private static final long serialVersionUID = -41912569433751270L;
    
    private Long detailId;

    private Long orderId;
    
    private Long storeId;
    
    private Long spuId;
    
    private Long skuId;
    
    private Double price;
    
    private Integer number;

    private Integer detailStatus;

    private Long cartId;

    private Spu spu;

    @Override
    public String toString() {
        return "Detail{" +
                "detailId=" + detailId +
                ", orderId=" + orderId +
                ", storeId=" + storeId +
                ", spuId=" + spuId +
                ", skuId=" + skuId +
                ", price=" + price +
                ", number=" + number +
                ", detailStatus=" + detailStatus +
                ", cartId=" + cartId +
                ", spu=" + spu +
                '}';
    }

    public Integer getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}

