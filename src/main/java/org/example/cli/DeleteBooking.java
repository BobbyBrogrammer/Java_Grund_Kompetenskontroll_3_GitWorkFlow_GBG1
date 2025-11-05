package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;
import org.example.systemIO.IIO;

import java.util.List;
import java.util.Optional;

public class DeleteBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final Repository<Booking, Integer> bookingRepository;
    private final IIO io;

    public DeleteBooking(InputHandler input, OutputHandler output, Repository<Booking, Integer> bookingRepository, IIO io) {
        this.input = input;
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.io = io;
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
