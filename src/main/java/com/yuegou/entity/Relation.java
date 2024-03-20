package com.yuegou.entity;

import java.io.Serializable;

/**
 * (Relation)实体类
 *
 * @author makejava
 * @since 2024-03-14 09:41:37
 */
public class Relation implements Serializable {
    private static final long serialVersionUID = 658845771749635562L;

    private Long relationId;

    private Long storeId;

    private Long spuId;

    @Override
    public String toString() {
        return "Relation{" +
                "relationId=" + relationId +
                ", storeId=" + storeId +
                ", spuId=" + spuId +
                '}';
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
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

}

