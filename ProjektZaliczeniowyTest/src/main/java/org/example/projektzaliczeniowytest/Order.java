package org.example.projektzaliczeniowytest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private int orderID;
    private int clientID;
    private List<OrderItem> orderItems = new ArrayList<>();
    private OrderStatus orderStatus;

    public Order(int orderID ,int clientID, List<OrderItem> orderItems) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderItems = orderItems;
        this.orderStatus = OrderStatus.NOWE;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        clientID = clientID;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
