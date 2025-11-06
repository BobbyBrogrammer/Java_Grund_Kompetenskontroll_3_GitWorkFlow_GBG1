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
    private boolean flexiblePrice;
    private double finalPrice;



    public Booking(Vehicle vehicle, LocalDate date, double price, Customer customer, BookingType bookingType) {
        this.id = counter++;
        this.vehicle = vehicle;
        this.date = date;
        this.price = price;
        this.customer = customer;
        this.bookingType = bookingType;
        this.status = Status.NOT_DONE;
        this.flexiblePrice = (bookingType == BookingType.REPAIR);
        this.finalPrice = 0.0;
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
    public boolean isFlexiblePrice() {
        return flexiblePrice;
    }

    public void setFlexiblePrice(boolean flexiblePrice) {
        this.flexiblePrice = flexiblePrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        String priceInfo;
        if (flexiblePrice) {
            priceInfo = (finalPrice > 0)
                    ? "Slutpris: " + finalPrice + " kr (flexibelt)"
                    : "Pris: Ej satt ännu (flexibelt)";
        } else {
            priceInfo = "Pris: " + price + " kr";
        }

        return "Booking ID: " + id +
                " | Typ: " + bookingType +
                " | Fordon: " + vehicle +
                " | Datum: " + date +
                " | " + priceInfo +
                " | Status: " + status +
                " | Kund: " + customer;
    }
}


