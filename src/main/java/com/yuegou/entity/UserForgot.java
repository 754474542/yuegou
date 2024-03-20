package com.yuegou.entity;

public class UserForgot {
    private User user;
    private String code;

    @Override
    public String toString() {
        return "UserForgot{" +
                "user=" + user +
                ", code='" + code + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserForgot() {
    }

    public UserForgot(User user, String code) {
        this.user = user;
        this.code = code;
    }
}
