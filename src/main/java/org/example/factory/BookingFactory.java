package org.example.factory;

import org.example.models.*;
import org.example.service.LoggingService;
import org.example.service.PriceService;
import java.time.LocalDate;

public class BookingFactory {
    private final PriceService priceService;
    private final LoggingService logger;


    public BookingFactory(PriceService priceService, LoggingService logger) {
        this.priceService = priceService;
        this.logger = logger;
    }


    public Booking bookInspection(Vehicle vehicle, LocalDate localDate, Customer customer){
        double price = priceService.getInspectionPrice();
        Booking booking = new Booking(vehicle, localDate, price, customer, BookingType.INSPECTION);
        logger.logInfo("Bokning för bil " + vehicle.getRegistrationNumber()
        + "åt kund " + customer.getName()
        + "klockan " + localDate
        + "för priset " + price);
        return booking;
    }

    public Booking bookService(Vehicle vehicle, LocalDate date, Customer customer, double price) {
       Booking booking = new Booking(vehicle, date, price, customer, BookingType.SERVICE);
        logger.logInfo("Skapade SERVICE-bokning för bil " + vehicle.getRegistrationNumber()
                + " åt kund " + customer.getName()
                + " datum " + date
                + " för priset " + price);
        return booking;
    }

    public Booking bookRepair(Vehicle vehicle, LocalDate date, Customer customer) {
        double price = 0.0;
        Booking booking = new Booking(vehicle, date, price, customer, BookingType.REPAIR);
        booking.setStatus(Status.NOT_DONE);
        logger.logInfo("Skapade REPARATIONS-bokning för bil " + vehicle.getRegistrationNumber()
                + " åt kund " + customer.getName()
                + " datum " + date
                + " (status satt till NOT_DONE)");
        return booking;
    }
}
