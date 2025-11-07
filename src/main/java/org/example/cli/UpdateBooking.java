package org.example.cli;

import org.example.models.Booking;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.repository.Repository;
import org.example.service.CompletionService;
import org.example.service.LoggingService;
import org.example.systemIO.IIO;
import  org.example.models.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * UpdateBooking ansvarar för att uppdatera en bokning.
 *
 * Använder:
 * - InputHandler för att läsa användarens val och data.
 * - OutputHandler och IIO för att visa och kommunicera med användaren.
 * - Repository<Booking, Integer> som internt använder Map för snabb uppslagning av bokningar via ID.
 *
 * Datastrukturer:
 * - Intern Map i repository möjliggör O(1)-uppslagning vid findById, update och remove.
 * - findAll() returnerar en List<Booking> som kan sorteras och itereras över för att visa bokningar.
 * - Map<String, Runnable> används för att koppla användarval till specifika uppdateringsaktioner.
 */
public class UpdateBooking {
    private final InputHandler input; // Hanterar användarinput
    private final OutputHandler output; // Hanterar utskrifter
    private final Repository<Booking, Integer> bookingRepository; // Bokningsrepository (Map bakom kulisserna)
    private final IIO io; // Input/output
    private final LoggingService logger; // Loggar händelser
    private final CompletionService completionService; // Hanterar notifiering när bokning är klar

    public UpdateBooking(InputHandler input,
                         OutputHandler output,
                         Repository<Booking, Integer> bookingRepository,
                         IIO io,
                         LoggingService logger,
                         CompletionService completionService) {
        this.input = input;
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.io = io;
        this.logger = logger;
        this.completionService = completionService;
    }

    /**
     * Uppdatera en bokning:
     * - Visa alla bokningar (sorterade efter ID)
     * - Fråga användaren vilken bokning som ska uppdateras
     * - Hämta bokning via repository (O(1) med Map)
     * - Utför vald uppdatering med hjälp av en Map<String, Runnable> för enkel hantering
     */
    public void updateBooking() {
        output.printShowAllBookingsTitle();
        List<Booking> allBookings = bookingRepository.findAll(); // Hämta alla bokningar som lista

        if (allBookings.isEmpty()) {
            output.printIfNoBookings();
            return;
        }

        // Visa alla bokningar, sorterade efter ID
        allBookings.stream()
                .sorted((Booking a, Booking b) -> Integer.compare(a.getId(), b.getId()))
                .forEach(b -> io.printLine(b.toString()));

        // Läs ID för bokningen som ska uppdateras
        int bookingId = input.readBookingId();
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isEmpty()) {
            output.printNoBookingFoundToUpdate();
            return;
        }

        Booking booking = optionalBooking.get();
        Customer customer = booking.getCustomer();
        Vehicle vehicle = booking.getVehicle();

        // Läs användarens val av uppdatering
        String choice = input.readUpdateChoice();

        // Hantera "gå tillbaka" eller avsluta
        if (choice.equals("9")) {
            output.printBackSpaceChoice();
            return;
        }
        if (choice.equals("10")) {
            output.printExitProgram();
            System.exit(0);
        }

        /**
         * Map med val -> Runnable.
         * - Varje tangentval motsvarar en uppdateringsaktion.
         * - Gör det enkelt att lägga till eller ändra uppdateringsalternativ.
         */
        Map<String, Runnable> actions = Map.ofEntries(
                Map.entry("1", () -> customer.setName(input.readCustomerName())),
                Map.entry("2", () -> customer.setPhoneNumber(input.readPhoneNumber())),
                Map.entry("3", () -> customer.setEmail(input.readEmail())),
                Map.entry("4", () -> vehicle.setModel(input.readVehicleModel())),
                Map.entry("5", () -> vehicle.setRegistrationNumber(input.readRegistrationNumber())),
                Map.entry("6", () -> vehicle.setYearModel(input.readYearModel())),
                Map.entry("7", () -> booking.setDate(input.readDate())),
                Map.entry("8", () -> {
                    Status newStatus = input.readBookingStatus();

                    // Om mekanikern markerar en reparation som klar (flexibelt pris)
                    if (newStatus == Status.DONE && booking.isFlexiblePrice()) {
                        output.printCompleteRepairTitle();
                        double repairPrice = input.readRepairPrice();

                        // Domänlogik: sätt slutpris och status DONE
                        booking.complete(repairPrice);
                    } else {
                        booking.setStatus(newStatus);
                    }
                })
        );

        // Hämta vald action från Map och kör den
        Runnable action = actions.get(choice);
        if (action == null) {
            output.printUnkownChoice();
            return;
        }

        action.run(); // Kör uppdateringen

        // Visa uppdaterad bokning
        output.printBookingHaveBeenUpdated(booking);

        // Om status är DONE, notifiera CompletionService
        if ("8".equals(choice) && booking.getStatus() == Status.DONE) {
            completionService.notifyVehicleReady(booking);
        }
    }
}