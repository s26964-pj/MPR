package org.example.storage;

import org.example.rental.Rental;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component

public class RentalStorage {
    private List<Rental> rentals = new ArrayList<>();
    public List<Rental> getAllRental(){
        return rentals;
    }
    public void addRental(Rental rental){
        rentals.add(rental);
    }
    public List<Rental> findRentalByVin(String vin) {
        return getAllRental()
                .stream()
                .filter(rental -> rental.getCar().getVin().equals(vin))
                .collect(Collectors.toList());
    }
}
