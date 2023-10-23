package org.example.storage;
import org.example.cars.Car;
import java.util.ArrayList;
import java.util.List;

public class CarStorage {
    private List<Car> cars = new ArrayList<>();
    private static CarStorage instance;
    public List<Car> getAllCars(){
        return cars;
    }

    private CarStorage() {
    }

    public void addCar(Car car){
        cars.add(car);
    }
    public static CarStorage getInstance() {
        if (instance == null) {
            instance = new CarStorage();
        }
        return instance;
    }
}


