package com.afs.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(outputStream));
    }

    //Case 1 -
    // Given a parking lot, and a car,
    // When park the car,
    // Then return a parking ticket.
    @Test
    void should_park_car_and_return_ticket_when_park() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A234123");
        Ticket expected = new Ticket(car, 1, parkingLot);
        Ticket actual = parkingLot.park(car);
        assertEquals(expected, actual);
    }

    //Case 2 -
    // Given a parking lot with a parked car, and a parking ticket,
    // When fetch the car,
    // Then return the parked car.
    @Test
    void should_return_parked_car_when_fetch_with_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A234123");
        Ticket ticket = parkingLot.park(car);
        Car fetched = parkingLot.fetch(ticket);
        assertEquals(car, fetched);
    }

    //Case 3 -
    // Given a parking lot with two parked cars, and two parking tickets,
    // When fetch the car twice,
    // Then return the right car with each ticket
    @Test
    void should_return_right_car_when_fetch_twice_with_two_tickets() {
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car("A234123");
        Car car2 = new Car("B234123");
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);
        assertNotNull(ticket1);
        assertNotNull(ticket2);

        Car fetched1 = parkingLot.fetch(ticket1);
        Car fetched2 = parkingLot.fetch(ticket2);
        assertEquals(car1, fetched1);
        assertEquals(car2, fetched2);
    }

    //Case 4 -
    // Given a parking lot, and a wrong parking ticket,
    // When fetch the car,
    // Then return nothing.
    @Test
    void should_return_null_when_fetch_with_wrong_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A234123");
        Ticket good = parkingLot.park(car);
        Ticket wrong = new Ticket(new Car("WRONG"), 1,parkingLot);
        assertNull(parkingLot.fetch(wrong));
        assertEquals(car, parkingLot.fetch(good));
    }
    //Case 5 -
    // Given a parking lot, and a used parking ticket,
    // When fetch the car,
    // Then return nothing.
    @Test
    void should_return_null_when_fetch_with_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A234123");
        Ticket ticket = parkingLot.park(car);
        assertEquals(car, parkingLot.fetch(ticket));
        assertNull(parkingLot.fetch(ticket));
    }
    //Case 6 -
    // Given a parking lot without any position, and a car,
    // When park the car,
    // Then return nothing
    @Test
    void should_return_null_when_park_with_no_position() {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 10; i++) {
            parkingLot.park(new Car("A23412" + i));
        }
        assertNull(parkingLot.park(new Car("A234999")));
    }

    //Case 7 - Given a parking lot, and an unrecognized ticket, When fetch the car, Then return nothing with error message "Unrecognized parking ticket.”
    @Test
    void should_return_null_and_error_message_when_fetch_with_unrecognized_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A234123");
        parkingLot.park(car);
        Ticket unrecognizedTicket = new Ticket(new Car("B234123"), 1, parkingLot);
        assertNull(parkingLot.fetch(unrecognizedTicket));
        assertTrue(outputStream.toString().contains("Unrecognized parking ticket."));
    }
    //Case 8 - Given a parking lot, and a used ticket, When fetch the car, Then return nothing with error message "Unrecognized parking ticket."
    @Test
    void should_return_null_and_error_message_when_fetch_with_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("A234123");
        Ticket ticket = parkingLot.park(car);
        assertEquals(car, parkingLot.fetch(ticket));
        assertNull(parkingLot.fetch(ticket));
        assertTrue(outputStream.toString().contains("Unrecognized parking ticket."));
    }
    //Case 9 - Given a parking lot without any position, and a car, When park the car, Then return nothing with error message "No available position."
    @Test
    void should_return_null_and_error_message_when_park_with_no_position() {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 10; i++) {
            parkingLot.park(new Car("A23412" + i));
        }
        assertNull(parkingLot.park(new Car("A234999")));
        assertTrue(outputStream.toString().contains("No available position."));
    }
    //Case 10 - Given a parking boy, and a null car, When park the car, Then return nothing.
    @Test
    void should_return_null_when_park_null_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = parkingBoy.park(null);
        assertNull(ticket);
    }
    //Case 11 - Given a parking boy, and a null ticket, When fetch the car, Then return nothing with error message "Unrecognized parking ticket."
    @Test
    void should_return_null_and_error_message_when_fetch_with_null_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car fetched = parkingBoy.fetch(null);
        assertNull(fetched);
        assertTrue(outputStream.toString().contains("Unrecognized parking ticket."));
    }

    @Test
    void should_park_car_to_second_lot_when_first_is_full() {
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot();
        ParkingBoy boy = new ParkingBoy(lot1, lot2);
        for (int i = 0; i < 10; i++) {
            boy.park(new Car("A" + i));
        }
        Ticket ticket = boy.park(new Car("B1"));
        assertNotNull(ticket);
        Car fetched = boy.fetch(ticket);
        assertNotNull(fetched);
        assertEquals("B1", fetched.getCarNumber());
    }

    @Test
    void should_return_null_when_all_lots_are_full() {
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot();
        ParkingBoy boy = new ParkingBoy(lot1, lot2);
        for (int i = 0; i < 20; i++) {
            boy.park(new Car("A" + i));
        }
        Ticket ticket = boy.park(new Car("B1"));
        assertNull(ticket);
        assertTrue(outputStream.toString().contains("No available position."));
    }

    @Test
    void should_fetch_car_from_correct_lot_with_valid_ticket() {
        ParkingLot lot1 = new ParkingLot();
        ParkingLot lot2 = new ParkingLot();
        ParkingBoy boy = new ParkingBoy(lot1, lot2);
        Car car1 = new Car("A1");
        Ticket ticket1 = boy.park(car1);
        Car car2 = new Car("B1");
        Ticket ticket2 = boy.park(car2);
        Car fetched1 = boy.fetch(ticket1);
        Car fetched2 = boy.fetch(ticket2);
        assertEquals(car1, fetched1);
        assertEquals(car2, fetched2);
    }
}
