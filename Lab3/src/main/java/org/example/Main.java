package org.example;

import org.example.cars.Car;
import org.example.cars.Type;
import org.example.services.RentalService;
import org.example.storage.CarStorage;
import org.example.storage.RentalStorage;
import org.example.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Main {
    private final CarStorage carStorage;
    private final RentalService rentalService;
    public Main(CarStorage carStorage, RentalService rentalService){
        this.carStorage = carStorage;
        this.rentalService = rentalService;
    }

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
}