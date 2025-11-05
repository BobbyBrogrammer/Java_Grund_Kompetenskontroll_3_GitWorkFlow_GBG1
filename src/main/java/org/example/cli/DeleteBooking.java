package org.example.cli;

import org.example.service.LoggingService;

import java.util.List;

public class DeleteBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final LoggingService logger;

    public DeleteBooking(InputHandler input, OutputHandler output, LoggingService logger) {
        this.input = input;
        this.output = output;
        this.logger = logger;
    }

    public void deleteBooking(List<String> bookings) {
        logger.logInfo("Radera bokning har startats ");
        output.printRemoveBooking();
        String search = input.readRegistrationNumber();
        boolean removed = bookings.removeIf(b -> b.contains(search));

        if (removed) {
            output.printBookingWasRemoved();
            logger.logInfo("Bokning har blivit raderat. ");
        } else {
            output.printNoBookingFoundToRemove();
        }
    }
}
