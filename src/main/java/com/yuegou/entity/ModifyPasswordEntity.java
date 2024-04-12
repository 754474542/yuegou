package com.yuegou.entity;

public class ModifyPasswordEntity {

    private String userName;
    private Integer userId;
    private String oldPassword;
    private String newPassword;

    @Override
    public String toString() {
        return "ModifyPasswordEntity{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
