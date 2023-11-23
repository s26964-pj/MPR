package org.example.rental;

import org.example.cars.Car;
import org.example.user.User;

import java.time.LocalDate;
import java.util.Date;

public class Rental {
    private User user;
    private Car car;
    private LocalDate startRental;
    private LocalDate endRental;
    private double price;

    public Rental(User user, Car car, LocalDate startRental, LocalDate endRental, double price) {
        this.user = user;
        this.car = car;
        this.startRental = startRental;
        this.endRental = endRental;
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getStartRental() {
        return startRental;
    }

    public void setStartRental(LocalDate startRental) {
        this.startRental = startRental;
    }

    public LocalDate getEndRental() {
        return endRental;
    }

    public void setEndRental(LocalDate endRental) {
        this.endRental = endRental;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "user=" + user +
                ", car=" + car +
                ", startRental=" + startRental +
                ", endRental=" + endRental +
                '}';
    }
}
