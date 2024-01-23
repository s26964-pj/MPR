package org.example.projektzaliczeniowytest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private int orderID;
    private int clientID;
    private List<Item> itemsList;
    private String adress;
    private OrderStatus orderStatus;

    public Order(int orderID ,int clientID, List<Item> orderItems, String adress) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.itemsList = orderItems;
        this.adress = adress;
        this.orderStatus = OrderStatus.NOWE;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", orderItems=" + itemsList +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
