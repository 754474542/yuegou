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

    
    private Long storeId;
    
    private Long userId;
    /**
     * 评价
     */
    private String evaluateContent;
    /**
     * 打分1-5
     */
    private Integer evaluateFraction;

    @Override
    public String toString() {
        return "GoodEvaluate{" +
                "evaluateId=" + evaluateId +
                ", storeId=" + storeId +
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

