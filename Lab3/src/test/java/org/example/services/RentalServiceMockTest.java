package org.example.services;

import org.example.cars.Car;
import org.example.cars.Type;
import org.example.rental.Rental;
import org.example.services.RentalService;
import org.example.storage.CarStorage;
import org.example.storage.RentalStorage;
import org.example.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalServiceMockTest{
    @InjectMocks
    private RentalService rentalServiceMoj;
    @Mock
    private RentalStorage rentalStorage;
    @Mock
    private CarStorage carStorage;

    @Test
    void isAvailable_future_returns_true() {
        //given
        Car car = createCar();
        String vin = car.getVin();
        LocalDate dateFrom = LocalDate.of(2024, 1, 10);
        LocalDate dateTo = LocalDate.of(2024, 1, 15);
        List<Rental> rentalList = createRentalList();

        //when
        when(carStorage.findCarByVin(vin)).thenReturn(Optional.of(car));
        when(rentalStorage.findRentalByVin(vin)).thenReturn(rentalList);
        boolean available = rentalServiceMoj.isAvailable(vin, dateFrom, dateTo);

        //then
        assertEquals(Boolean.TRUE, available);
    }

    @Test
    void isAvailable_future_returns_false() {
        //given
        Car car = createCar();
        String vin = car.getVin();
        LocalDate dateFrom = LocalDate.of(2023, 12, 7);
        LocalDate dateTo = LocalDate.of(2023, 12, 10);
        List<Rental> rentalList = createRentalList();

        //when
        when(carStorage.findCarByVin(vin)).thenReturn(Optional.of(car));
        when(rentalStorage.findRentalByVin(vin)).thenReturn(rentalList);
        boolean available = rentalServiceMoj.isAvailable(vin, dateFrom, dateTo);

        //then
        assertEquals(Boolean.FALSE, available);
    }

    @Test
    void isAvailable_past_returns_true() {
        //given
        Car car = createCar();
        String vin = car.getVin();
        LocalDate dateFrom = LocalDate.of(2024, 11, 26);
        LocalDate dateTo = LocalDate.of(2024, 11, 27);
        List<Rental> rentalList = createRentalList();

        //when
        when(carStorage.findCarByVin(vin)).thenReturn(Optional.of(car));
        when(rentalStorage.findRentalByVin(vin)).thenReturn(rentalList);
        boolean available = rentalServiceMoj.isAvailable(vin, dateFrom, dateTo);

        //then
        assertEquals(Boolean.TRUE, available);
    }

    @ParameterizedTest
    @MethodSource("falseDates")
    void isAvailable_past_withParameters_returnsFalse(LocalDate startDate, LocalDate endDate) {
        //given
        Car car = createCar();
        String vin = car.getVin();
        List<Rental> rentalList = createRentalList();

        //when
        when(carStorage.findCarByVin(vin)).thenReturn(Optional.of(car));
        when(rentalStorage.findRentalByVin(vin)).thenReturn(rentalList);
        boolean available = rentalServiceMoj.isAvailable(vin, startDate, endDate);

        //then
        assertEquals(Boolean.FALSE, available);
    }

    @Test
    void rentCar_returns_true() {
        //given
        Car car = createCar();
        LocalDate dateFrom = LocalDate.of(2023, 12, 7);
        LocalDate dateTo = LocalDate.of(2023, 12, 9);

        //when
        when(carStorage.findCarByVin(car.getVin())).thenReturn(Optional.of(car));
        Rental rental = rentalServiceMoj.rentCar(1, car.getVin(), dateFrom, dateTo);

        //then
        assertEquals(rental.getCar(), car);
    }

    @Test
    void rentCar_returns_false() {
        //given
        Car car = createCar();
        LocalDate dateFrom = LocalDate.of(2023, 12, 10);
        LocalDate dateTo = LocalDate.of(2024, 1, 9);

        //when
        when(carStorage.findCarByVin(car.getVin())).thenReturn(Optional.of(car));
        when(rentalStorage.findRentalByVin(car.getVin())).thenReturn(createRentalList());
        //then
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(
                        () -> rentalServiceMoj.rentCar(1, car.getVin(), dateFrom, dateTo));
    }

    @ParameterizedTest
    @EnumSource(Type.class)
    void estimatePrice(Type type) {
        //given
        String vin = "123456789";
        Car car = new Car("Ford", "Focus", vin, type);
        LocalDate dateFrom = LocalDate.of(2023, 10, 7);
        LocalDate dateTo = LocalDate.of(2023, 10, 9);
        //when
        //then
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(
                        () -> rentalServiceMoj.estimatePrice(car.getVin(), dateFrom, dateTo));
    }

    @ParameterizedTest
    @MethodSource("inputData")
    void shouldHaveOverlappingDates(LocalDate startDate, LocalDate endDate) {
        Car car = createCar();
        String vin = car.getVin();
        List<Rental> rentalList = createRentalList();
        when(carStorage.findCarByVin(vin)).thenReturn(Optional.of(car));
        when(rentalStorage.findRentalByVin(vin)).thenReturn(rentalList);
        boolean available = rentalServiceMoj.isAvailable("123456789", startDate, endDate);

        assertThat(available).isTrue();
    }

    public static Stream<Arguments> inputData() {
        return Stream.of(
                Arguments.of(LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 30)),
                Arguments.of(LocalDate.of(2024, 11, 25), LocalDate.of(2024, 11, 30)),
                Arguments.of(LocalDate.of(2024, 1, 25), LocalDate.of(2024, 1, 30)),
                Arguments.of(LocalDate.of(2024, 1, 28), LocalDate.of(2024, 2, 28)),
                Arguments.of(LocalDate.of(2024, 1, 25), LocalDate.of(2024, 11, 30)),
                Arguments.of(LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 7))
        );
    }

    public static Stream<Arguments> falseDates() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 12, 1), LocalDate.of(2024, 1, 30)),
                Arguments.of(LocalDate.of(2023, 12, 1), LocalDate.of(2024, 1, 5)),
                Arguments.of(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 1, 9)),
                Arguments.of(LocalDate.of(2023, 11, 25), LocalDate.of(2024, 1, 30)),
                Arguments.of(LocalDate.of(2023, 11, 25), LocalDate.of(2024, 11, 5)),
                Arguments.of(LocalDate.of(2023, 11, 25), LocalDate.of(2023, 12, 30))
        );
    }

    private Car createCar() {
        String vin = "123456789";
        return new Car("Ford", "Focus", vin, Type.ECONOMY);
    }

    private List<Rental> createRentalList() {
        LocalDate dateFrom = LocalDate.of(2023, 12, 8);
        LocalDate dateTo = LocalDate.of(2024, 1, 8);
        return List.of(new Rental(new User(1), createCar(), dateFrom, dateTo, 50d));
    }
}