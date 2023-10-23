package org.example;

import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Pizza pizza1 = new Pizza("xd",Size.SMALL,19.99);
        Pizza pizza2 = new Pizza("xd2",Size.LARGE,39.99);
        Drink drink1 = new Drink("Coke", 23);

        Order Order = new Order(1, List.of(pizza1,pizza2,drink1));
        OrderStatus orderStatus= Order.placeOrder();
        System.out.println("Status: "+orderStatus.status()+" price: "+ orderStatus.price());
        Order.getList();
        System.out.println(List.of(pizza1.getName(),pizza2,drink1.getName()));
    }
}