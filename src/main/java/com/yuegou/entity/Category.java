package com.yuegou.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2024-03-13 10:01:09
 */
public class Category implements Serializable {
    private static final long serialVersionUID = -54262233080299364L;
    /**
     * 商品类别id
     */
    private Long categoryId;
    /**
     * 类别名称
     */
    private String categoryName;
    /**
     * 上级类别id
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer categorySort;
    /**
     * 创建时间
     */
    private Date createTime;

    private String parentName;

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", parentId=" + parentId +
                ", categorySort=" + categorySort +
                ", createTime=" + createTime +
                ", parentName='" + parentName + '\'' +
                '}';
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parent_name) {
        this.parentName = parent_name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(Integer categorySort) {
        this.categorySort = categorySort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

