package org.example.projektzaliczeniowytest;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderStorage {
    private int orderID;
    private List<Order> orders = new ArrayList<>();
    public List<Order> getOrders(){
        return orders;
    }
    public void addOrder(int orderID, Order order){
        orders.add(order);
    }
    public Optional<Order> findOrderById(int orderID){
        return getOrders()
                .stream()
                .filter(order -> order.getOrderID() == orderID)
                .findFirst();
    }
}
