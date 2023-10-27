package org.example.services;

import org.example.cars.Car;
import org.example.cars.Type;
import org.example.rental.Rental;
import org.example.storage.CarStorage;
import org.example.storage.RentalStorage;
import org.example.user.User;

import java.util.Date;

public class RentalServiceStream {

    //rent --------------------------------------------------------------------
    public void rent(User user, String vin, Date dateFrom, Date dateTo) {
        Rental rental = new Rental(user, findCarByVin(vin), dateFrom, dateTo);

        if (findCarByVin(vin) != null) {
            if (findRentalByCar(findCarByVin(vin)) == null) {
                RentalStorage.getInstance().addRental(rental);
                System.out.println("Udało się zarezerwowac auto");
            } else {
                if (findRentalByCar(findCarByVin(vin)).getEndRental().after(dateTo)) {
                    System.out.println("Car isn't available");
                } else if (findRentalByCar(findCarByVin(vin)).getEndRental().before(dateTo)) {
                    RentalStorage.getInstance().addRental(rental);
                    System.out.println("Udało się zarezerwowac auto");
                }
            }
        } else {
            System.out.println("Incorrect vin");
        }
    }

    //check rental status

    public void isAvailable(String vin, Date dateFrom, Date dateTo) {
        if (findCarByVin(vin) != null) {
            if (findRentalByCar(findCarByVin(vin)) == null) {
                System.out.println("Car is available");
            } else {
                if (findRentalByCar(findCarByVin(vin)).getEndRental().after(dateTo)) {
                    System.out.println("Car isn't available");
                } else if (findRentalByCar(findCarByVin(vin)).getEndRental().before(dateTo)) {
                    System.out.println("Car is avaiable");
                }
            }
            System.out.println("Car isn't available");
        } else {
            System.out.println("Incorrect vin");
        }
    }

    //calculate Price

    public void estimataePrice(String vin, Date dateFrom, Date dateTo) {

        double cena = 0;
        long roznicaCzasu = dateTo.getTime() - dateFrom.getTime();
        long roznicaDni = roznicaCzasu / (1000 * 60 * 60 * 24);

        if (findCarByVin(vin).getType().equals(Type.ECONOMY)) {
            cena = roznicaDni * 200;
        } else if (findCarByVin(vin).getType().equals(Type.PREMIUM)) {
            cena = roznicaDni * 500;
        } else if (findCarByVin(vin).getType().equals(Type.STANDARD)) {
            cena = roznicaDni * 350;
        }
        System.out.println("Cena wynajmu będzie wynosić około: " + cena + " zł");
        System.out.printf("roznica dni " + roznicaDni);
    }
    private Car findCarByVin(String vin) {
        return CarStorage.getInstance()
                .getAllCars()
                .stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst()
                .orElse(null);
    }
    private Rental findRentalByCar(Car car) {
        return RentalStorage.getInstance()
                .getAllRental()
                .stream()
                .filter(rental -> rental.getCar().equals(car))
                .findFirst()
                .orElse(null);
    }
}