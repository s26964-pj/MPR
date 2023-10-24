package org.example;

import org.example.cars.Car;
import org.example.cars.Type;
import org.example.services.RentalService;
import org.example.storage.CarStorage;
import org.example.rental.Rental;
import org.example.storage.RentalStorage;
import org.example.user.User;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CarStorage.getInstance().addCar(new Car("Fiat", "Bravo","asdasd", Type.STANDARD));
        CarStorage.getInstance().addCar(new Car("Audi","A3", "wafasf", Type.PREMIUM));
        User Dominik = new User(1);

        Date dateFrom = new Date(2023,11,13);
        Date dateTo = new Date(2023,12,29);

        Date dateFrom1 = new Date(2023,10,13);
        Date dateTo1 = new Date(2023,12,29);

        Date dateFrom2 = new Date(2023,12,13);
        Date dateTo2 = new Date(2024,12,29);

        RentalService rentalService = new RentalService();
        rentalService.rent(Dominik, "asdasd", dateFrom , dateTo);
        rentalService.isAvailable("asdasd", dateFrom1,dateTo1);
        rentalService.rent(Dominik, "asdasd", dateFrom1, dateTo1);
        //todo rentalService.isAvailable(vin, dateFrom, dateTo)
        //todo rentalService.estimatePrice(vin, dateFrom, dateTo)


    }
}