// java
package com.afs.parkinglot;

import java.util.Objects;

public class Ticket {
    private Car car;
    private Integer position;
    private ParkingLot parkingLot;

    public Ticket(Car car, Integer position, ParkingLot parkingLot) {
        this.car = car;
        this.position = position;
        this.parkingLot = parkingLot;
    }

    public Car getCar() {
        return car;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(car, ticket.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car);
    }

    @Override
    public String toString() {
        return "Ticket{" + "car=" + car + '}';
    }
}
