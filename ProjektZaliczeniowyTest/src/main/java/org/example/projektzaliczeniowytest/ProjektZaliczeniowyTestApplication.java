package org.example.projektzaliczeniowytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjektZaliczeniowyTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjektZaliczeniowyTestApplication.class, args);

        OrderStorage orderStorage = new OrderStorage();
        ProductStorage productStorage = new ProductStorage();
        OrderServices orderServices = new OrderServices(orderStorage, productStorage);

        productStorage.addNewProduct(new Product("Jajecznica", 10));

        //Składanie zamówienia
        System.out.println(orderServices.placeOrder(1, 2, Arrays.asList(new Item("Jajecznica", 5)), "Gdansk"));

        //Status zamówienia

        System.out.println(orderServices.orderStatus(1));

        //Wynik anulowania zamówienia

        System.out.println(orderServices.orderCancel(1));

        //Wynik potwierdzenia dostarczenia

        System.out.println(orderServices.confirmOrder(1));
    }
}