package org.example.storage;
import org.example.cars.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class CarStorage {
    private List<Car> cars = new ArrayList<>();
    public List<Car> getAllCars(){
        return cars;
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public Optional<Car> findCarByVin(String vin){
        return getAllCars()
                .stream()
                .filter(car -> car.getVin().equals(vin))
                .findFirst();
    }
}


