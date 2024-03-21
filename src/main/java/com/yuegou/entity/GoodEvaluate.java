package com.yuegou.entity;

import java.io.Serializable;

/**
 * (GoodEvaluate)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:09
 */
public class GoodEvaluate implements Serializable {
    private static final long serialVersionUID = -27748900205394644L;
    
    private Long evaluateId;

    private Long spuId;

    private Long skuId;
    
    private Long userId;
    /**
     * 评价
     */
    private String evaluateContent;
    /**
     * 打分1-5
     */
    private Integer evaluateFraction;

    public GoodEvaluate() {
    }

    @Override
    public String toString() {
        return "GoodEvaluate{" +
                "evaluateId=" + evaluateId +
                ", spuId=" + spuId +
                ", skuId=" + skuId +
                ", userId=" + userId +
                ", evaluateContent='" + evaluateContent + '\'' +
                ", evaluateFraction=" + evaluateFraction +
                '}';
    }

    public Long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Long evaluateId) {
        this.evaluateId = evaluateId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Integer getEvaluateFraction() {
        return evaluateFraction;
    }

    public void setEvaluateFraction(Integer evaluateFraction) {
        this.evaluateFraction = evaluateFraction;
    }
}

