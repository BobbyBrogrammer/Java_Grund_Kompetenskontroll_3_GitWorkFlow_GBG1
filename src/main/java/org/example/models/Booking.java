package org.example.models;

import java.io.ObjectInputFilter;
import java.time.LocalDate;


public class Booking {
    private static int counter = 1;
    private int id;
    private Vehicle vehicle;
    private LocalDate date;
    private double price;
    private Customer customer;        // <--- ändrat från String
    private Status status;
    private BookingType bookingType;

    public Booking(Vehicle vehicle, LocalDate date, double price, Customer customer, BookingType bookingType) {
        this.id = counter++;
        this.vehicle = vehicle;
        this.date = date;
        this.price = price;
        this.customer = customer;
        this.bookingType = bookingType;
        this.status = Status.NOT_DONE;
    }

    // Getters och setters
    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    @Override
    public String toString() {
        return "Booking ID: " + id +
                " | Type: " + bookingType +
                " | Vehicle: " + vehicle +
                " | Date: " + date +
                " | Price: " + price +
                " | Status: " + status +
                " | Customer: " + customer;
    }


}


