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

        String idInput = io.readLine().trim();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException ex) {
            output.printError("Ogiltigt ID, endast siffror är tillåtna!");
            logger.logError("Felaktigt ID vid borttagning: " + idInput);
            return;
        }

        // Hitta bokningen via repository
        Optional<Booking> bookingToDelete = bookingRepository.findById(id);

        if (bookingToDelete.isEmpty()) {
            output.printNoBookingFoundToRemove();
            logger.logInfo("Ingen bokning hittades med ID: " + id);
            return;
        }

        Booking booking = bookingToDelete.get();

        // Visa bokningen som hittats
        output.printBookingFound();
        io.printLine(booking.toString());

        // Bekräfta borttagning
        output.printConfirmDeleteQuestion();

        while (true) {
            String answer = io.readLine().trim().toUpperCase();

            if (answer.equals("Y") || answer.equals("J")) {
                bookingRepository.remove(id);
                output.printBookingWasRemoved();
                logger.logInfo("Bokning med ID " + id + " togs bort: " + booking);
                break;
            } else if (answer.equals("N")) {
                output.printDeleteCancelled();
                logger.logInfo("Borttagning av bokning med ID " + id + " avbröts av användaren.");
                break;
            } else {
                output.printError("Ogiltigt svar. Skriv Y/N (Y)för ja eller (N) för nej:");
            }
        }
    }
}