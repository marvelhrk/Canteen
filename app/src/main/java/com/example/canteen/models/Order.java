package com.example.canteen.models;

import java.util.List;

public class Order {
    String id;
    String userId;
    String orderId;
    String date;
    String total;
    String status;
    String name;
    String address;
    String phonenum;
    List<Cart> cart;

    public Order() {
    }

    public Order(String id, String userId,String orderId, String date, String total, String status, String name, String address, String phonenum, List<Cart> cart) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.date = date;
        this.total = total;
        this.status = status;
        this.name = name;
        this.address = address;
        this.phonenum = phonenum;
        this.cart = cart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}