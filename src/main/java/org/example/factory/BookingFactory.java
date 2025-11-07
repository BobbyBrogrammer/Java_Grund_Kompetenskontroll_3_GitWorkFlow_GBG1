package org.example.factory;

import org.example.models.*;
import org.example.service.LoggingService;
import org.example.service.PriceService;
import java.time.LocalDate;

/**
 * BookingFactory ansvarar för att skapa olika typer av bokningar.
 *
 * Varje metod returnerar ett nytt Booking-objekt med korrekt typ (INSPECTION, SERVICE, REPAIR)
 * och sätter relevanta attribut som pris, datum, kund och status.
 */
public class BookingFactory {
    private final PriceService priceService; // Beräknar pris för olika tjänster
    private final LoggingService logger;     // Loggar information

    public BookingFactory(PriceService priceService, LoggingService logger) {
        this.priceService = priceService;
        this.logger = logger;
    }

    /**
     * Skapar en bokning för besiktning.
     * - Pris hämtas från PriceService.
     * - Status sätts automatiskt beroende på typ.
     * - Loggar information och skickar fiktivt mejl till kund.
     */
    public Booking bookInspection(Vehicle vehicle, LocalDate localDate, Customer customer){
        double price = priceService.getInspectionPrice();
        Booking booking = new Booking(vehicle, localDate, price, customer, BookingType.INSPECTION);

        logger.logInfo("Bokning för bil " + vehicle.getRegistrationNumber()
                + " åt kund " + customer.getName()
                + " klockan " + localDate
                + " för priset " + price);

        // 📧 Fiktivt mejl till kund
        logger.logInfo("📧 Mejl skickat till " + customer.getEmail() +
                ":\nHej " + customer.getName() +
                "! Tack för din bokning av besiktning. Du ska lämna in din bil (" +
                vehicle.getRegistrationNumber() + ") den " + localDate +
                ".\n\nVänliga hälsningar,\nVerkstaden");

        return booking;
    }

    /**
     * Skapar en servicebokning.
     * - Pris ges som parameter.
     * - Loggar information och skickar fiktivt mejl till kund.
     */
    public Booking bookService(Vehicle vehicle, LocalDate date, Customer customer, double price) {
        Booking booking = new Booking(vehicle, date, price, customer, BookingType.SERVICE);

        logger.logInfo("Skapade SERVICE-bokning för bil " + vehicle.getRegistrationNumber()
                + " åt kund " + customer.getName()
                + " datum " + date
                + " för priset " + price);

        // 📧 Fiktivt mejl till kund
        logger.logInfo("📧 Mejl skickat till " + customer.getEmail() +
                ":\nHej " + customer.getName() +
                "! Tack för din bokning av service. Du ska lämna in din bil (" +
                vehicle.getRegistrationNumber() + ") den " + date +
                ".\n\nVänliga hälsningar,\nVerkstaden");

        return booking;
    }

    /**
     * Skapar en reparationsbokning.
     * - Pris sätts till 0 initialt.
     * - Status sätts till NOT_DONE.
     * - Loggar information och skickar fiktivt mejl till kund.
     */
    public Booking bookRepair(Vehicle vehicle, LocalDate date, Customer customer) {
        double price = 0.0;
        Booking booking = new Booking(vehicle, date, price, customer, BookingType.REPAIR);
        booking.setStatus(Status.NOT_DONE);

        logger.logInfo("Skapade REPARATIONS-bokning för bil " + vehicle.getRegistrationNumber()
                + " åt kund " + customer.getName()
                + " datum " + date
                + " (status satt till NOT_DONE)");

        // 📧 Fiktivt mejl till kund
        logger.logInfo("📧 Mejl skickat till " + customer.getEmail() +
                ":\nHej " + customer.getName() +
                "! Tack för din bokning av reparation. Du ska lämna in din bil (" +
                vehicle.getRegistrationNumber() + ") den " + date +
                ".\n\nVänliga hälsningar,\nVerkstaden");

        return booking;
    }
}