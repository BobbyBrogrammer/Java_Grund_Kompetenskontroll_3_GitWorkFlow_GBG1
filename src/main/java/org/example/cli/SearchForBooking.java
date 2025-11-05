package org.example.cli;

import org.example.service.LoggingService;

import java.util.List;

public class SearchForBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final LoggingService logger;

    public SearchForBooking(InputHandler input, OutputHandler output, LoggingService logger) {
        this.input = input;
        this.output = output;
        this.logger = logger;
    }

    public void searchBooking(List<String> bookings) {
        output.printSearchForBookingTitle();
        String search = input.readRegistrationNumber();
        logger.logInfo("Sökning av registreringsnummer startad " + search);

        List<String> bookingFound = bookings.stream()
                .filter(b -> b.contains(search))
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
