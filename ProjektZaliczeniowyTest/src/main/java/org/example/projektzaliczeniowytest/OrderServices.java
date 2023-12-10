package org.example.projektzaliczeniowytest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServices {
    private OrderStorage orderStorage;

    public OrderServices(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }

    //Złożenie zamówienia
    public void placeOrder(int orderID, int clientID, List<OrderItem> items) {
        Order order = new Order(orderID, clientID, items);
        System.out.println("Utworzono zamówienia o numerze: " + orderID);
    }

    //sprawdzenie status
    public Order statusOrder(int orderID) {
        Order order = orderStorage.findOrderById(orderID).orElseThrow(() -> new RuntimeException("Order not found"));
        return order;
    }


    //anulowanie zamówienia
    public Order calncelOrder(int orderID) {
        Order order = orderStorage.findOrderById(orderID).orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getOrderStatus().equals(OrderStatus.ANULOWANE)) {
            return order;
        }
        order.setOrderStatus(OrderStatus.ANULOWANE);
        return order;
    }

    //potwierdzenie dostarczenia zamówienia
    public Order delivieryOrder(int orderID) {
        Order order = orderStorage.findOrderById(orderID).orElseThrow(() -> new RuntimeException("Order not found"));
        if(order.getOrderStatus().equals(OrderStatus.DOSTARCZONE)) {

        }
        order.setOrderStatus(OrderStatus.DOSTARCZONE);
        return order;
    }


}
