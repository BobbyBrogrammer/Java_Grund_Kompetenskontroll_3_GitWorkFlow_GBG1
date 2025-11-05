package org.example.factory;

import org.example.models.*;
import org.example.service.PriceService;

import java.time.LocalDate;

public class BookingFactory {
    private final PriceService priceService;


    public BookingFactory(PriceService priceService) {
        this.priceService = priceService;
    }


    public Booking bookInspection(Vehicle vehicle, LocalDate localDate, Customer customer){
        double price = priceService.getInspectionPrice();
        return new Booking(vehicle, localDate, price, customer, BookingType.INSPECTION);
    }

    public Booking bookService(Vehicle vehicle, LocalDate date, Customer customer, double price) {
        return new Booking(vehicle, date, price, customer, BookingType.SERVICE);
    }

    public Booking bookRepair(Vehicle vehicle, LocalDate date, Customer customer) {
        double price = 0.0;
        Booking booking = new Booking(vehicle, date, price, customer, BookingType.REPAIR);
        booking.setStatus(Status.NOT_DONE);
        return booking;
    }

}
