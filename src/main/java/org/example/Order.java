package org.example;

import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class Order {
    private int id;
    private List<Product> productList;
    private boolean accepted = false;

    public Order(int id, List<Product> productList) {
        this.id = id;
        this.productList = productList;

    }

    public OrderStatus placeOrder() {
        //werifyOrder
        int hour = LocalDateTime.now().getHour();
        if (!productList.isEmpty() && hour <= 20) {
            accepted = true;
        }
        if (!accepted) {
            return new OrderStatus(false, 0);
        }
        //placeOrder
        double totalPrice = calculateTotalPrice();
        return new OrderStatus(true, totalPrice);
    }

    public double calculateTotalPrice() {
        return productList.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public void getList() {
        List<String> productNames = productList.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        System.out.println(productNames);
    }

}
