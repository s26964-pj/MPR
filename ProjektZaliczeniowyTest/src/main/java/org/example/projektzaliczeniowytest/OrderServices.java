package org.example.projektzaliczeniowytest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServices {
    private final OrderStorage orderStorage;

    public OrderServices(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }

    //Złożenie zamówienia
    public Order placeOrder(int orderID, int clientID, List<OrderItem> items) {
        return new Order(orderID, clientID, items);
    }

    //sprawdzenie status
    public Order statusOrder(int orderID) {
        Order order = getOrder(orderID);
        return order;
    }


    //anulowanie zamówienia
    public Order calncelOrder(int orderID) {
        Order order = getOrder(orderID);
        if (order.getOrderStatus().equals(OrderStatus.W_REALIZACJI)) {
            return order;
        }
        order.setOrderStatus(OrderStatus.ANULOWANE);
        return order;
    }

    //potwierdzenie dostarczenia zamówienia
    public Order delivieryOrder(int orderID) {
        Order order = getOrder(orderID);
        if(order.getOrderStatus().equals(OrderStatus.DOSTARCZONE)) {
            return order;
        }
        order.setOrderStatus(OrderStatus.DOSTARCZONE);
        return order;
    }

    private Order getOrder(int orderID) {
        Order order = orderStorage.findOrderById(orderID)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return order;
    }
}
