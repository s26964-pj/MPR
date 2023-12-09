package org.example.services;

import org.example.cars.Car;
import org.example.rental.Rental;
import org.example.storage.CarStorage;
import org.example.storage.RentalStorage;
import org.example.user.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class RentalService {
    private final CarStorage carStorage;
    private final RentalStorage rentalStorage;

    public RentalService(CarStorage carStorage, RentalStorage rentalStorage) {
        this.carStorage = carStorage;
        this.rentalStorage = rentalStorage;
    }

    public boolean isAvailable(String vin, LocalDate startDate, LocalDate endDate) {
        Car carByVin = carStorage.findCarByVin(vin).orElseThrow();

        List<Rental> rentalsForVin = rentalStorage.findRentalByVin(vin);

        if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            return false;
        }

        if (rentalsForVin.isEmpty()) {
            return true;
        }
        for (Rental rental : rentalsForVin) {
            if (isBetween(rental.getStartRental(), rental.getEndRental(), startDate) || isBetween(rental.getStartRental(), rental.getEndRental(), endDate)) {
                return false;
            }
            if (isBetween(startDate, endDate, rental.getStartRental()) || isBetween(startDate, endDate, rental.getEndRental())) {
                return false;
            }
        }
        return true;
    }

    private boolean isBetween(LocalDate periodStart, LocalDate periodEnd, LocalDate checkingDate) {
        return checkingDate.isAfter(periodStart) && checkingDate.isBefore(periodEnd);
    }

    public Rental rentCar(int userId, String vin, LocalDate startDate, LocalDate endDate) {
        Car carByVin = carStorage.findCarByVin(vin).orElseThrow();
        if (isAvailable(vin, startDate, endDate)) {
            double price = estimatePrice(vin, startDate, endDate);
            Rental rental = new Rental(new User(userId), carByVin, startDate, endDate, price);
            rentalStorage.addRental(rental);
            return rental;
        } else {
            throw new RuntimeException();
        }
    }

    public double estimatePrice(String vin, LocalDate startDate, LocalDate endDate) {
        Car car = carStorage.findCarByVin(vin).orElseThrow();
        double multiplier = car.getType().getMultiplier();
        long daysBetween = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        if (daysBetween < 0) {
            throw new RuntimeException("Invalid data");
        }
        return 500 * multiplier * daysBetween;
    }
}
