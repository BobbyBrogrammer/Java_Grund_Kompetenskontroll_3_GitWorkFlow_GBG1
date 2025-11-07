package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;
import org.example.service.LoggingService;
import org.example.systemIO.IIO;
import java.util.Optional;

/**
 * DeleteBooking hanterar borttagning av bokningar via användarinmatning.
 * Den använder Repository<Booking, Integer> för snabb åtkomst via boknings-ID.
 */
public class DeleteBooking {
    // Hanterar utskrift till användaren
    private final OutputHandler output;
    // Repository används för att hämta och ta bort bokningar
    // Den konkreta implementationen (BookingRepository) använder en LinkedHashMap
    // Map ger snabb åtkomst och unik nyckel per bokning (ID) – O(1) operationer
    private final Repository<Booking, Integer> bookingRepository;
    // Hanterar användarinmatning
    private final IIO io;
    // Loggar händelser och fel
    private final LoggingService logger;

    public DeleteBooking(OutputHandler output, Repository<Booking, Integer> bookingRepository, IIO io, LoggingService logger) {
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.io = io;
        this.logger = logger;
    }

    public void deleteBooking() {
        // Be användaren skriva in ID för bokning som ska tas bort
        output.printRemoveBooking();
        output.askForBookingId();

        String idInput = io.readLine().trim();

        int id;
        try {
            // Försöker konvertera inmatningen till ett heltal
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException ex) {
            // Felhantering om inmatningen inte är en siffra
            output.printError("Ogiltigt ID, endast siffror är tillåtna!");
            logger.logError("Felaktigt ID vid borttagning: " + idInput);
            return;
        }

        // Hitta bokningen via repository (Map ger snabb uppslagning)
        Optional<Booking> bookingToDelete = bookingRepository.findById(id);

        if (bookingToDelete.isEmpty()) {
            // Om ingen bokning hittas med angivet ID
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
                // Ta bort bokningen via repository (snabb borttagning med Map)
                bookingRepository.remove(id);
                output.printBookingWasRemoved();
                logger.logInfo("Bokning med ID " + id + " togs bort: " + booking);
                break;
            } else if (answer.equals("N")) {
                // Avbryt borttagning
                output.printDeleteCancelled();
                logger.logInfo("Borttagning av bokning med ID " + id + " avbröts av användaren.");
                break;
            } else {
                // Ogiltigt svar från användaren
                output.printError("Ogiltigt svar. Skriv Y/N (Y)för ja eller (N) för nej:");
            }
        }
    }
}