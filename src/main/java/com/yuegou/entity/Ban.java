package com.yuegou.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Ban)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:09
 */
public class Ban implements Serializable {
    private static final long serialVersionUID = -20774623774110355L;
    
    private Long userId;
    
    private Date banTime;
    
    private Date banUntime;
    
    private Integer banServerit;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getBanTime() {
        return banTime;
    }

    public void setBanTime(Date banTime) {
        this.banTime = banTime;
    }

    public Date getBanUntime() {
        return banUntime;
    }

    public void setBanUntime(Date banUntime) {
        this.banUntime = banUntime;
    }

    public Integer getBanServerit() {
        return banServerit;
    }

    public void setBanServerit(Integer banServerit) {
        this.banServerit = banServerit;
    }

}

