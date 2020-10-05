package com.example.egyptimportandexport.data.model;

public class Order {
    String orderlId;
    String orderName;

    public Order(String orderlId, String orderName) {
        this.orderlId = orderlId;
        this.orderName = orderName;
    }

    public String getOrderlId() {
        return orderlId;
    }

    public String getOrderName() {
        return orderName;
    }
}
