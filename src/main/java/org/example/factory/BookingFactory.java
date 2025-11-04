package org.example.factory;

import org.example.models.Booking;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.service.PriceService;

import java.time.LocalDate;

public class BookingFactory {
    private final PriceService priceService;


    public BookingFactory(PriceService priceService) {
        this.priceService = priceService;
    }


    public Booking bookInspection(Vehicle vehicle, LocalDate localDate, Customer customer){
        double price = priceService.bookInspection();
        return new Booking(vehicle, localDate, price, customer);
    }
}
