package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;
import org.example.systemIO.IIO;

import java.util.Comparator;
import java.util.List;

/**
 * ShowAllBookings ansvarar för att visa alla bokningar i systemet.
 *
 * Den använder OutputHandler för utskrift och IIO för att läsa användarens val.
 * Bokningar hämtas via bookingRepository som internt använder en Map<Integer, Booking>.
 *
 * Datastruktur:
 * - Map används i repository för att snabbt kunna hitta, uppdatera och ta bort bokningar via ID.
 * - findAll() returnerar en List<Booking> som används för att sortera och iterera över alla bokningar.
 */
public class ShowAllBookings {
    private final OutputHandler output; // Hanterar utskrifter
    private final Repository<Booking, Integer> bookingRepository; // Repository med Map bakom
    private final IIO io; // Läsning av användarinput

    public ShowAllBookings(OutputHandler output, Repository<Booking, Integer> bookingRepository, IIO io) {
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.io = io;
    }

    /**
     * Visa alla bokningar, med möjlighet att sortera.
     */
    public void showAllBookings() {
        output.printShowAllBookingsTitle(); // Visa titel
        List<Booking> allBookings = bookingRepository.findAll(); // Hämta alla bokningar från Map

        if (allBookings.isEmpty()) {
            output.printIfNoBookings(); // Om inga bokningar finns, visa meddelande
            return;
        }

        output.printSortOptions(); // Visa sorteringsalternativ
        String sortChoice = io.readLine().trim(); // Läs användarens val

        // Välj sorteringskriterium baserat på användarens val
        Comparator<Booking> comparator = switch (sortChoice) {
            case "1" -> Comparator.comparing(b -> b.getId());      // Sortera efter ID
            case "2" -> Comparator.comparing(b -> b.getDate());    // Sortera efter datum
            case "3" -> Comparator.comparing(b -> b.getStatus());  // Sortera efter status
            default -> Comparator.comparing(b -> b.getId());       // Default: ID
        };

        // Sortera och skriv ut varje bokning
        allBookings.stream()
                .sorted(comparator)
                .forEach(b -> io.printLine(b.toString()));
    }

    /**
     * Läs boknings-ID från användaren.
     * Kontrollerar att input är ett positivt heltal.
     */
    public int readBookingId() {
        while (true) {
            output.askForBookingId(); // Be användaren skriva ID
            try {
                int id = Integer.parseInt(io.readLine().trim()); // Konvertera input till int
                if (id > 0) {
                    return id; // Returnera ID om det är giltigt
                }
                output.printWrongNumberInput(); // Felmeddelande om <0
            } catch (NumberFormatException e) {
                output.printWrongNumberInput(); // Felmeddelande om input inte är ett tal
            }
        }
    }
}
