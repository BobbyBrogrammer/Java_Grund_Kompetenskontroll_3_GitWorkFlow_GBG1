package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;
import org.example.service.LoggingService;
import org.example.systemIO.IIO;


public class UpdateBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final Repository<Booking, Integer> bookingRepository;
    private final IIO io;
    private final LoggingService logger;

    public UpdateBooking(InputHandler input, OutputHandler output, Repository<Booking, Integer> bookingRepository, IIO io, LoggingService logger) {
        this.input = input;
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.io = io;
        this.logger = logger;
    }

    public void updateBooking() {
        output.printUpdateBookTitle();
        output.askForBookingId();
        try {
            int id = Integer.parseInt(io.readLine());
            bookingRepository.findAll().stream()
                    .filter(b -> b.getId() == id)
                    .findFirst()
                    .ifPresentOrElse(b -> {
                        output.printStateCarModel();
                        String newModel = input.readVehicleModel();
                        b.getVehicle().setModel(newModel);
                        output.printBookingHaveBeenUpdated();
                        output.printSuccess("Uppdaterad bokning: " + b);
                    }, () -> output.printNoBookingFoundToUpdate());

        } catch (NumberFormatException ex) {
            output.printError("Ogiltigt ID, endast siffror är tillåtet!");
        }
    }
}

