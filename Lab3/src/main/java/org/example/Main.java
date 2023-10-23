package org.example;

import org.example.cars.Car;
import org.example.cars.Type;
import org.example.storage.CarStorage;
import org.example.rental.Rental;
import org.example.storage.RentalStorage;
import org.example.user.User;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CarStorage.getInstance().addCar(new Car("Fiat", "Bravo","asdasd", Type.STANDARD));
        CarStorage.getInstance().addCar(new Car("Audi","A3", "wafasf", Type.PREMIUM));
        System.out.println(CarStorage.getInstance().getAllCars());
        User Dominik = new User(1);

        RentalStorage.getInstance().addRental(new Rental(Dominik,CarStorage.getInstance().getAllCars().get(0),new Date(2023,11,13), new Date(2023,12,29)));
        System.out.println(RentalStorage.getInstance().getAllRental());
    }
}