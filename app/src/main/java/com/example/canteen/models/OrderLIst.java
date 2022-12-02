package com.example.canteen.models;

import java.util.List;

public class OrderLIst {
    List<Order> orderList;

    public OrderLIst(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
