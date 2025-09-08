package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {



    //Case 1 - Given a parking lot, and a car, When park the car, Then return a parking ticket.
    @Test
    void should_park_car_and_return_ticket_when_park() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A234123");
        Ticket ticket = parkingLot.park(car);
        assertNotNull(ticket);
    }
    //Case 2 - Given a parking lot with a parked car, and a parking ticket, When fetch the car, Then return the parked car.
//    @Test
//    void should_return_parked_car_when_fetch_with_valid_ticket() {
//        ParkingLot parkingLot = new ParkingLot();
//        Car car = new Car("A234123");
//        Ticket ticket =new Ticket();
//        parkingLot.fetch(ticket);
//        Car fetchedCar = parkingLot.fetch(ticket);
//        assertEquals(car, fetchedCar);
//    }
    //Case 3 - Given a parking lot with two parked cars, and two parking tickets, When fetch the car twice, Then return the right car with each ticket
//    @Test
//    void should_return_right_car_when_fetch_twice_with_two_tickets() {
//        ParkingLot parkingLot = new ParkingLot();
//        Car car1 = new Car("A234123");
//        Car car2 = new Car("B234123");
//        Ticket ticket1 = parkingLot.park(car1);
//        Ticket ticket2 = parkingLot.park(car2);
//        Car fetchedCar1 = parkingLot.fetch(ticket1);
//        Car fetchedCar2 = parkingLot.fetch(ticket2);
//        assertEquals(car1, fetchedCar1);
//        assertEquals(car2, fetchedCar2);
//    }
    //Case 4 - Given a parking lot, and a wrong parking ticket, When fetch the car, Then return nothing.
    //Case 5 - Given a parking lot, and a used parking ticket, When fetch the car, Then return nothing.
    //Case 6 - Given a parking lot without any position, and a car, When park the car, Then return nothing



}
