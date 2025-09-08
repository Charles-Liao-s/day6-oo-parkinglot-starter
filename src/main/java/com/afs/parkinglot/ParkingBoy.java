package com.afs.parkinglot;

public class ParkingBoy {
    private final ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        if (car == null) {
            return null;
        }
        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            System.out.println("Unrecognized parking ticket.");
            return null;
        }
        return parkingLot.fetch(ticket);
    }
}
