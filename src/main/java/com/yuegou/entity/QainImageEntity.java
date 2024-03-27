package com.yuegou.entity;

public class QainImageEntity {
    private String imagePath;

    public QainImageEntity() {
    }

    @Override
    public String toString() {
        return "QainImageEntity{" +
                "imagePath='" + imagePath + '\'' +
                '}';
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
