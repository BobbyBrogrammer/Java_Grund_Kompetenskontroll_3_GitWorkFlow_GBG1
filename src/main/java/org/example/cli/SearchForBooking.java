package org.example.cli;

import org.example.service.LoggingService;

import org.example.models.Booking;
import org.example.repository.Repository;

import java.util.List;

public class SearchForBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final LoggingService logger;
    private final Repository<Booking, Integer> bookingRepository;

    public SearchForBooking(InputHandler input, OutputHandler output, LoggingService logger) {
    public SearchForBooking(InputHandler input, OutputHandler output, Repository<Booking, Integer> bookingRepository) {
        this.input = input;
        this.output = output;
        this.logger = logger;
        this.bookingRepository = bookingRepository;
    }

    public void searchBooking(List<String> bookings) {
        output.printSearchForBookingTitle();
        String search = input.readRegistrationNumber();
        logger.logInfo("Sökning av registreringsnummer startad " + search);

        List<String> bookingFound = bookingRepository.findAll().stream()
                .filter(b -> b.getVehicle().getRegistrationNumber().equalsIgnoreCase())
                .toList();

        if (bookingFound.isEmpty()) {
            output.printNoBookingFound();
            logger.logInfo("Inga bokningar med detta registreringsnummer hittades" + search);
        } else {
            output.printBookingFound();
            bookingFound.forEach(output::printSuccess);
            logger.logInfo("Hittad " + bookingFound.size() + "bokning(s) för registreringsnummer " + search);
        }
    }
}
