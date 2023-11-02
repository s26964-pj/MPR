package org.example.services;

import org.example.cars.Car;
import org.example.cars.Type;
import org.example.rental.Rental;
import org.example.storage.CarStorage;
import org.example.storage.RentalStorage;
import org.example.user.User;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class RentalService {

    //rent
    public void rent(User user, String vin, Date dateFrom, Date dateTo) {
        if (findCarByVin(vin) != null) {
            if (checkAvailable(vin, dateFrom, dateTo)) {
                Rental rental = new Rental(user, findCarByVin(vin), dateFrom, dateTo);
                RentalStorage.getInstance().addRental(rental);
                System.out.println("Udało się zarezerwowac auto");
            } else if (!checkAvailable(vin, dateFrom, dateTo)) {
                System.out.println("Auto jest zarezerwowane w tym terminie");
            }
        } else {
            System.out.println("Podałeś nieprawidłowy vin");
        }
    }

    //check rental status

    public void isAvailable(String vin, Date dateFrom, Date dateTo) {
        if (checkAvailable(vin, dateFrom, dateTo) == true) {
            System.out.println("Auto jest dostępne");
        } else {
            System.out.println("Auto jest niedostępne");
        }
    }

    public boolean checkAvailable(String vin, Date dateFrom, Date dateTo) {
        if (findCarByVin(vin) != null) {
            if (findRentalByVin(vin) == null) {
                return true;
            } else {
                if (findRentalByVin(vin).getEndRental().after(dateTo)) {
                    return false;
                } else if (findRentalByVin(vin).getEndRental().before(dateTo)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
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

    //Find car and rental
    private Car findCarByVin(String vin) {
        return CarStorage.getInstance()
                .getAllCars()
                .stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst()
                .orElse(null);
    }

    private Rental findRentalByVin(String vin) {
        return RentalStorage.getInstance()
                .getAllRental()
                .stream()
                .filter(rental -> rental.getCar().equals(findCarByVin(vin)))
                .findFirst()
                .orElse(null);
    }
}
