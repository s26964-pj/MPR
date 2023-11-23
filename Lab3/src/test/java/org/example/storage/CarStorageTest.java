package org.example.storage;

import junit.framework.TestCase;
import org.example.cars.Car;
import org.example.cars.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CarStorageTest extends TestCase {
    private CarStorage carStorage = CarStorage.getInstance();

    @BeforeEach
    void cleanUp(){
        carStorage.purgeDatabase();
    }

    @Test
    void shouldHaveEntriesInStorage(){
        //GIVEN
        carStorage.addCar(new Car("aada", "dsada", "dsadsa" , Type.PREMIUM));

        //WHEN
        List<Car> allCars = carStorage.getAllCars();

        //THEN
        assertThat(allCars).hasSize(1);
    }

    @Test
    void shouldHaveEntriesInStorage2(){
        //GIVEN
        carStorage.addCar(new Car("aada","dsada", "dsadsa", Type.PREMIUM));

        //WHEN
        List<Car> allCars = carStorage.getAllCars();

        //THEN
        assertThat(allCars).hasSize(1);
    }

}