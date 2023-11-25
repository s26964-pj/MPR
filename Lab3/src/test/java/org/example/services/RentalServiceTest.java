package org.example.services;

import net.bytebuddy.asm.Advice;
import org.example.cars.Car;
import org.example.cars.Type;
import org.example.rental.Rental;
import org.example.storage.CarStorage;
import org.example.storage.RentalStorage;
import org.example.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RentalServiceTest {
    private CarStorage carStorage = CarStorage.getInstance();
    private RentalStorage rentalStorage = RentalStorage.getInstance();
    private RentalService rentalService = new RentalService(carStorage, rentalStorage);

    @BeforeEach
    void cleanUp() {
        carStorage.purgeDatabase();
        rentalStorage.purgeDatabase();
    }

    @ParameterizedTest
    @EnumSource(Type.class)
    void estimatePrice0(Type type) {
        //GIVEN
        Car car = new Car("asdasd", "asdasd", "asdasdaa", Type.STANDARD);
        carStorage.addCar(car);

        //WHEN
        //THEN
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(
                        () -> rentalService.estimatePrice(car.getVin(),
                                LocalDate.now(),
                                LocalDate.now().minusDays(1))
                );
    }


    @Test
    void estimatePrice1() {
        //GIVEN
        carStorage.addCar(new Car("Audi", "A3", "abcd", Type.STANDARD));
        LocalDate startDate = LocalDate.of(2019, 06, 13);
        LocalDate endDate = LocalDate.of(2019, 06, 30);

        //WHEN
        double price = rentalService.estimatePrice("abcd", startDate, endDate);

        //THEN
        assertThat(price).isEqualTo(12750);
    }

    @Test
    void estimatePrice2() {
        carStorage.addCar(new Car("Audi", "A3", "abcd", Type.ECONOMY));
        LocalDate startDate = LocalDate.of(2019, 06, 13);
        LocalDate endDate = LocalDate.of(2019, 06, 30);

        //WHEN
        double price = rentalService.estimatePrice("abcd", startDate, endDate);

        //THEN
        assertThat(price).isEqualTo(8500);
    }

    @Test
    void estimatePrice3() {
        carStorage.addCar(new Car("Audi", "A3", "abcd", Type.PREMIUM));
        LocalDate startDate = LocalDate.of(2019, 06, 13);
        LocalDate endDate = LocalDate.of(2019, 06, 30);

        //WHEN
        double price = rentalService.estimatePrice("abcd", startDate, endDate);

        //THEN
        assertThat(price).isEqualTo(25500);
    }

    @ParameterizedTest
    @MethodSource("inputDataTrue")
    void isAvailableTrue(LocalDate startDate, LocalDate endDate) {
        User user = new User(1);
        Car car = new Car("make", "model", "abc", Type.PREMIUM);
        carStorage.addCar(car);
        Rental rental = new Rental(
                user,
                car,
                LocalDate.of(2019, 06, 10),
                LocalDate.of(2019, 06, 13),
                1234);
        rentalStorage.addRental(rental);

        boolean abc = rentalService.isAvailable(
                car.getVin(),
                startDate,
                endDate
        );

        assertThat(abc).isTrue();
    }

    public static Stream<Arguments> inputDataTrue() {
        return Stream.of(
                Arguments.of(LocalDate.of(2019, 06, 5), LocalDate.of(2019, 06, 9)),
                Arguments.of(LocalDate.of(2019, 06, 15), LocalDate.of(2019, 06, 20)),
                Arguments.of(LocalDate.of(2019, 06, 14), LocalDate.of(2019, 06, 19)),
                Arguments.of(LocalDate.of(2019, 06, 1), LocalDate.of(2019, 06, 7))
        );
    }

    @ParameterizedTest
    @MethodSource("inputDataFalse")
    void isAvailableFalse(LocalDate startDate, LocalDate endDate) {
        User user = new User(1);
        Car car = new Car("make", "model", "abc", Type.PREMIUM);
        carStorage.addCar(car);
        Rental rental = new Rental(
                user,
                car,
                LocalDate.of(2019, 06, 10),
                LocalDate.of(2019, 06, 13),
                1234);
        rentalStorage.addRental(rental);

        boolean abc = rentalService.isAvailable(
                car.getVin(),
                startDate,
                endDate
        );

        assertThat(abc).isFalse();
    }

    public static Stream<Arguments> inputDataFalse() {
        return Stream.of(
                Arguments.of(LocalDate.of(2019, 06, 5), LocalDate.of(2019, 06, 12)),
                Arguments.of(LocalDate.of(2019, 06, 12), LocalDate.of(2019, 06, 20)),
                Arguments.of(LocalDate.of(2019, 06, 7), LocalDate.of(2019, 06, 19)),
                Arguments.of(LocalDate.of(2019, 06, 11), LocalDate.of(2019, 06, 12))
        );
    }
}