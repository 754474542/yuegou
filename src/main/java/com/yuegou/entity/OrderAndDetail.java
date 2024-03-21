package com.yuegou.entity;

import java.util.List;

public class OrderAndDetail {
    private Order order;
    private List<Detail> detailList;

    @Override
    public String toString() {
        return "OrderAndDetail{" +
                "order=" + order +
                ", detailList=" + detailList +
                '}';
    }

    public OrderAndDetail() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }
}
