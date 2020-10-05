package com.example.egyptimportandexport.data.model;

import com.google.gson.annotations.SerializedName;

public class OrderAPI {
    @SerializedName("order")
    private String order;

    public OrderAPI(String order) {
        this.order = order;
    }
}
