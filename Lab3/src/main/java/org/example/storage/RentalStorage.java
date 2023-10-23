package org.example.storage;

import org.example.rental.Rental;

import java.util.ArrayList;
import java.util.List;

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
}
