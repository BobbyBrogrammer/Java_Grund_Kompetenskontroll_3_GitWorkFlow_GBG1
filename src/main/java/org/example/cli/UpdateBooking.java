package org.example.cli;

import org.example.service.LoggingService;

import java.util.List;

public class UpdateBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final LoggingService logger;

    public UpdateBooking(InputHandler input, OutputHandler output, LoggingService logger) {
        this.input = input;
        this.output = output;
        this.logger = logger;
    }

    public void updateBooking(List<String> bookings) {
        output.printUpdateBookTitle();
        String search = input.readRegistrationNumber();
        logger.logInfo("Uppdatera bokningar startad för registreringsnummer " + search);

        bookings.stream()
                .filter(b -> b.contains(search))
                .findFirst()
                .ifPresentOrElse(foundBooking -> {
                    output.printStateCarModel();
                    String newVehicle = input.readVehicleModel();
                    int index = bookings.indexOf(foundBooking);
                    bookings.set(index, search + " (" + newVehicle + ")");
                    output.printBookingHaveBeenUpdated();
                    logger.logInfo("Bokning uppdaterad för registreringsnummer " + search);
                }, () -> {
                    output.printNoBookingFoundToUpdate();
                    logger.logInfo("Inge bokning hittad för att uppdatera registreringsnummer " + search);
                });
        }
    }

