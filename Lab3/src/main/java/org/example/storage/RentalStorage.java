package org.example.storage;

import org.example.cars.Car;
import org.example.rental.Rental;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RentalStorage {
    private List<Rental> rentals = new ArrayList<>();
    private static RentalStorage instance;
    private  void RentalStorage(){
    }

    public void addRental(Rental rental){
        rentals.add(rental);
    }
    public List<Rental> getAllRental(){
        return rentals;
    }
    public static RentalStorage getInstance() {
        if (instance == null) {
            instance = new RentalStorage();
        }
        return instance;
    }
    public List<Rental> findRentalByVin(String vin) {
        return getAllRental()
                .stream()
                .filter(rental -> rental.getCar().getVin().equals(vin))
                .collect(Collectors.toList());
    }
    public void purgeDatabase(){
        rentals.clear();
    }
}
