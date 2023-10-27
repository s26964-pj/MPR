package org.example;

import org.example.cars.Car;
import org.example.cars.Type;
import org.example.services.RentalServiceStream;
import org.example.storage.CarStorage;
import org.example.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        //Format date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //car storage, add cars
        CarStorage.getInstance().addCar(new Car("Fiat", "Bravo","asdasd", Type.STANDARD));
        CarStorage.getInstance().addCar(new Car("Audi","A3", "wafasf", Type.PREMIUM));

        //create user
        User Dominik = new User(1);

        //Rental date
        Date dateFrom = sdf.parse("2023-11-13");
        Date dateTo = sdf.parse("2023-12-29");

        Date dateFrom1 = sdf.parse("2023-10-13");
        Date dateTo1 = sdf.parse("2023-12-29");

        Date dateFrom2 = sdf.parse("2023-12-30");
        Date dateTo2 = sdf.parse("2024-12-29");

        //Services
        //RentalService rentalService = new RentalService();
        //rentalService.rent(Dominik, "asdasd", dateFrom , dateTo);
        //rentalService.isAvailable("asdasd", dateFrom1,dateTo1);
        //rentalService.rent(Dominik, "asdasd", dateFrom1, dateTo1);
        //rentalService.estimataePrice("asdasd", dateFrom, dateTo);

        RentalServiceStream rentalServiceStream = new RentalServiceStream();

        rentalServiceStream.rent(Dominik,"asdasd", dateFrom , dateTo);
        rentalServiceStream.isAvailable("asdasd", dateFrom2,dateTo2);
        rentalServiceStream.rent(Dominik,"asdasd", dateFrom2, dateTo2);

    }
}