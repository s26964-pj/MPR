package org.example.projektzaliczeniowytest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServices {
    private OrderStorage orderStorage;

    public OrderServices(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }

    //Złożenie zamówienia
    public void placeOrder(int orderID, int clientID, List<OrderItem> items){
        Order order = new Order(orderID, clientID, items);
        System.out.println("Utworzono zamówienia o numerze: " + orderID);
    }

    //sprawdzenie status
    public Order statusOrder(int orderID){
        Order order = orderStorage.findOrderById(orderID).orElseThrow(() -> new RuntimeException("Order not found"));
        if(order != null){

        }else{

        }
    return order;
    }

    //anulowanie zamówienia

    //potwierdzenie dostarczenia zamówienia


}
