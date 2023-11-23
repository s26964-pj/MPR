package org.example;

import org.example.cars.Car;
import org.example.cars.Type;
import org.example.services.RentalService;
import org.example.storage.CarStorage;
import org.example.storage.RentalStorage;
import org.example.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        RentalStorage rentalStorage = RentalStorage.getInstance();
        CarStorage carStorage = CarStorage.getInstance();

        //Services
        RentalService rentalService = new RentalService(carStorage, rentalStorage);

        //car storage, add cars
        CarStorage.getInstance().addCar(new Car("Fiat", "Bravo","asdasd", Type.STANDARD));
        CarStorage.getInstance().addCar(new Car("Audi","A3", "wafasf", Type.PREMIUM));

        //create user
        User Dominik = new User(1);

        //Rental date

    }
}