package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;
import org.example.service.LoggingService;

import java.util.List;

/**
 * SearchForBooking ansvarar för att söka upp bokningar baserat på
 * registreringsnummer.
 *
 * Den använder InputHandler för att läsa användarens input och OutputHandler
 * för att skriva resultat till konsolen.
 *
 * Datastruktur:
 * Bokningar hämtas via bookingRepository som använder en Map<id, Booking>.
 * Vi använder findAll() för att skapa en lista över alla bokningar
 * och filtrerar sedan efter registreringsnummer.
 */
public class SearchForBooking {
    private final InputHandler input;  // Hanterar användarens input
    private final OutputHandler output; // Hanterar utskrift till användaren
    private final Repository<Booking, Integer> bookingRepository; // Repository för bokningar
    private final LoggingService logger; // Loggar händelser

    public SearchForBooking(InputHandler input, OutputHandler output, Repository<Booking, Integer> bookingRepository, LoggingService logger) {
        this.input = input;
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.logger = logger;
    }

    /**
     * Sök efter bokning med registreringsnummer.
     * Hämtar alla bokningar som lista från repository (Map används internt),
     * filtrerar på registreringsnummer och visar resultat.
     */
    public void searchBooking() {
        output.printSearchForBookingTitle(); // Visa titel
        String search = input.readRegistrationNumber(); // Läs registreringsnummer från användaren

        // Hämta alla bokningar från repository och filtrera efter registreringsnummer
        List<Booking> bookingFound = bookingRepository.findAll().stream()
                .filter(b -> b.getVehicle().getRegistrationNumber().equalsIgnoreCase(search))
                .toList();

        // Om inga bokningar hittas, skriv meddelande, annars skriv detaljer
        if (bookingFound.isEmpty()) {
            output.printNoBookingFound();
        } else {
            output.printBookingFound();
            bookingFound.forEach(b -> output.printBookingDetails(b.toString()));
        }
    }
}
