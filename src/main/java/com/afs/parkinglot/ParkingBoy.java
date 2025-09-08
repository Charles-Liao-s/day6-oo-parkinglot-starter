package com.afs.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = new ArrayList<>();
        for (ParkingLot lot : parkingLots) {
            this.parkingLots.add(lot);
        }
    }

    public Ticket park(Car car) {
        if (car == null) {
            return null;
        }

        for (ParkingLot lot : parkingLots) {
            Ticket ticket = lot.park(car);
            if (ticket != null) {
                return ticket;
            }
        }

        System.out.println("No available position.");
        return null;
    }

    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            System.out.println("Unrecognized parking ticket.");
            return null;
        }

        for (ParkingLot lot : parkingLots) {
            Car car = lot.fetch(ticket);
            if (car != null) {
                return car;
            }
        }

        System.out.println("Unrecognized parking ticket.");
        return null;
    }
}
