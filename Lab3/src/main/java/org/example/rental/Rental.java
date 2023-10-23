package org.example.rental;

import org.example.cars.Car;
import org.example.user.User;

import java.util.Date;

public class Rental {
    private User user;
    private Car car;
    private Date startRental;
    private Date endRental;

    public Rental(User user, Car car, Date startRental, Date endRental) {
        this.user = user;
        this.car = car;
        this.startRental = startRental;
        this.endRental = endRental;
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

    public Date getStartRental() {
        return startRental;
    }

    public void setStartRental(Date startRental) {
        this.startRental = startRental;
    }

    public Date getEndRental() {
        return endRental;
    }

    public void setEndRental(Date endRental) {
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
