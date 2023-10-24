package org.example.services;

import org.example.cars.Car;
import org.example.rental.Rental;
import org.example.storage.CarStorage;
import org.example.storage.RentalStorage;
import org.example.user.User;

import java.util.Date;

public class RentalService {

    //todo rent
    public void rent(User user, String vin, Date dateFrom, Date dateTo) {
        if()
        if (isAvailable(vin, dateFrom, dateTo)) {
            Rental rental = new Rental(user, findCar(vin), dateFrom, dateTo);
            RentalStorage.getInstance().addRental(rental);
            System.out.println("Udało się zarezerwowac auto");
        } else if (!isAvailable(vin, dateFrom, dateTo)) {
            System.out.println("Auto jest zarezerwowane w tym terminie");
        }

    }

    public Car findCar(String vin) {
        for (Car car : CarStorage.getInstance().getAllCars()) {
            if (car.getVin().equals(vin)) {
                return car;
            }
        }
        return null;
    }

    //todo isAvilable

    public boolean isAvailable(String vin, Date dateFrom, Date dateTo) {
        if (findRental(vin) == null) {
            return false;
        } else {
            if (findRental(vin).getEndRental().after(dateTo)) {
                //System.out.printf("Auto jest zarezerwowane w tym terminie");
                return false;
            } else if (findRental(vin).getEndRental().before(dateTo)) {
                return true;
            }
        }
        return false;
    }

    public Rental findRental(String vin) {
        for (Car car : CarStorage.getInstance().getAllCars()) {
            if (car.getVin().equals(vin)) {
                for (Rental rental : RentalStorage.getInstance().getAllRental()) {
                    if (rental.getCar().equals(car)) {
                        return rental;
                    }
                }
            }
        }
        return null;
    }

    //todo estimatePrice


}
