package com.yuegou.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (Store)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:10
 */
public class Store implements Serializable {
    private static final long serialVersionUID = 766721806076105233L;
    
    private Long storeId;
    
    private Long userId;
    
    private String storeName;
    
    private String storeIntroduce;
    
    private Integer storeBite;
    
    private Integer storeVisit;

    private List<Spu> spuList;

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", userId=" + userId +
                ", storeName='" + storeName + '\'' +
                ", storeIntroduce='" + storeIntroduce + '\'' +
                ", storeBite=" + storeBite +
                ", storeVisit=" + storeVisit +
                ", spuList=" + spuList +
                '}';
    }

    public List<Spu> getSpuList() {
        return spuList;
    }

    public void setSpuList(List<Spu> spuList) {
        this.spuList = spuList;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreIntroduce() {
        return storeIntroduce;
    }

    public void setStoreIntroduce(String storeIntroduce) {
        this.storeIntroduce = storeIntroduce;
    }

    public Integer getStoreBite() {
        return storeBite;
    }

    public void setStoreBite(Integer storeBite) {
        this.storeBite = storeBite;
    }

    public Integer getStoreVisit() {
        return storeVisit;
    }

    public void setStoreVisit(Integer storeVisit) {
        this.storeVisit = storeVisit;
    }

}

