package com.yuegou.entity;

import java.util.Date;
import java.util.List;

//用户
public class User {
    
    private Long userId;
    
    private String userName;
    
    private String userPhone;

    private String userPassword;
    
    private String userCard;
    
    private Integer userPower;
    
    private String userAddress;
    
    private String userHead;
    
    private Integer userGender;

    private Integer userEmpt;

    private Date emptCreate;

    private Store store;

    private List<Order> order;

    private List<Cart> cartList;

    private Ban ban;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userCard='" + userCard + '\'' +
                ", userPower=" + userPower +
                ", userAddress='" + userAddress + '\'' +
                ", userHead='" + userHead + '\'' +
                ", userGender=" + userGender +
                ", userEmpt=" + userEmpt +
                ", emptCreate=" + emptCreate +
                ", store=" + store +
                ", order=" + order +
                ", cartList=" + cartList +
                ", ban=" + ban +
                '}';
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public Integer getUserPower() {
        return userPower;
    }

    public void setUserPower(Integer userPower) {
        this.userPower = userPower;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public Integer getUserEmpt() {
        return userEmpt;
    }

    public void setUserEmpt(Integer userEmpt) {
        this.userEmpt = userEmpt;
    }

    public Date getEmptCreate() {
        return emptCreate;
    }

    public void setEmptCreate(Date emptCreate) {
        this.emptCreate = emptCreate;
    }

}

