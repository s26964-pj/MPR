package org.example.services;

import org.example.storage.CarStorage;

public class CarService {
    private final CarStorage carStorage;
    public CarService(CarStorage carStorage) {
        this.carStorage = carStorage;
    }
    public String getFirstCarName() {
        return carStorage.getCarNames("ABC").stream() .findFirst() .orElse("INVALID");
    }
}
