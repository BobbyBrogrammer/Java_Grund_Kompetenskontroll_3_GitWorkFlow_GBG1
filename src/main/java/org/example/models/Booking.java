package org.example.models;

import java.io.ObjectInputFilter;
import java.time.LocalDate;

    public class Booking {

        private static int counter = 1;
        private int id;
        private Vehicle vehicle;
        private LocalDate date;
        private double price;
        private String customerEmail;
        private Status status;
        private BookingType bookingType;


        public Booking(Vehicle vehicle, LocalDate date, double price, String customerEmail, BookingType bookingType) {
            this.id = counter++;
            this.vehicle = vehicle;
            this.date = date;
            this.price = price;
            this.customerEmail = customerEmail;
            this.bookingType = bookingType;
            this.status = Status.NOT_DONE;
        }
    }

