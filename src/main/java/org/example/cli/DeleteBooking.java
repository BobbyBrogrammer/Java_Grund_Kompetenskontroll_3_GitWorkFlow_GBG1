package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;
import org.example.service.LoggingService;
import org.example.systemIO.IIO;
import java.util.Optional;

public class DeleteBooking {
    private final OutputHandler output;
    private final Repository<Booking, Integer> bookingRepository;
    private final IIO io;
    private final LoggingService logger;

    public DeleteBooking(OutputHandler output, Repository<Booking, Integer> bookingRepository, IIO io, LoggingService logger) {
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.io = io;
        this.logger = logger;
    }

    public void deleteBooking() {
        output.printRemoveBooking();
        output.askForBookingId();
        try {
            int id = Integer.parseInt(io.readLine());
            Optional<Booking> bookingToDelete = bookingRepository.findAll().stream()
                    .filter(b -> b.getId() == id).findFirst();

            if (bookingToDelete.isPresent()) {
                bookingRepository.remove(id);
                output.printBookingWasRemoved();
                output.printSuccess("Borttagen bokning: " + bookingToDelete.get());
            } else {
                output.printNoBookingFoundToRemove();
            }

        } catch (NumberFormatException ex) {
            output.printError("Ogiltigt ID, endast siffror är tillåtet!");
        }
    }
}
