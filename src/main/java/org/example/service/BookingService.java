package org.example.service;

import org.example.models.Booking;
import org.example.models.BookingType;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.models.Status;
import org.example.repository.BookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * BookingService hanterar logiken för att skapa, visa och ta bort bokningar.
 * (Följer SRP och använder Repository för lagring)
 */
public class BookingService {

    private final BookingRepository bookingRepository;
    private final LoggingService loggingService;
    private final ValidationService validationService;
    private final PriceService priceService;

    // 🔹 Constructor med dependency injection
    public BookingService(BookingRepository bookingRepository,
                          LoggingService loggingService,
                          ValidationService validationService,
                          PriceService priceService) {
        this.bookingRepository = bookingRepository;
        this.loggingService = loggingService;
        this.validationService = validationService;
        this.priceService = priceService;
    }

    // --------------------------------------------------

    /**
     * Skapar en ny bokning om all data är giltig.
     */
    public void createBooking(Vehicle vehicle, LocalDate date, Customer customer, BookingType bookingType) {
        if (!validationService.isValidDate(date)) {
            System.out.println("❌ Ogiltigt datum!");
            return;
        }

        double price = priceService.calculatePrice(bookingType, vehicle);

        Booking booking = new Booking(vehicle, date, price, customer, bookingType);
        bookingRepository.add(booking);
        loggingService.logInfo("✅ Ny bokning skapad: " + booking);
    }

    // --------------------------------------------------

    /**
     * Hämtar alla bokningar
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // --------------------------------------------------

    /**
     * Filtrerar bokningar baserat på status (DONE / NOT_DONE)
     */
    public List<Booking> getBookingsByStatus(Status status) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getStatus() == status)
                .collect(Collectors.toList());
    }

    // --------------------------------------------------

    /**
     * Filtrerar bokningar per kund
     */
    public List<Booking> getBookingsByCustomer(String customerName) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getCustomer().getName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    // --------------------------------------------------

    /**
     * Markerar en bokning som färdig (DONE)
     */
    public void completeBooking(int bookingId) {
        bookingRepository.findById(bookingId).ifPresentOrElse(
                booking -> {booking.setStatus(Status.DONE);
                loggingService.logInfo("Bokning: " + bookingId + " är markerad som klar.");
                }, () -> loggingService.logError("Bokning med ID: " + bookingId + " hittades inte.")
        );
    }

    // --------------------------------------------------

    /**
     * Tar bort en bokning
     */
    public void removeBooking(int bookingId) {
        bookingRepository.findById(bookingId).ifPresentOrElse(
                booking -> {
                    bookingRepository.remove(bookingId);
                    loggingService.logInfo("Bokning med ID: " + booking + " har tagits bort.");
                },
                () -> loggingService.logError("Bokning med ID " + bookingId + " hittades inte.")
        );
    }

    // --------------------------------------------------

    /**
     * Skriver ut alla bokningar till konsolen
     */
    public void printAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        Optional.of(bookings).filter(list -> !list.isEmpty())
                .ifPresentOrElse(list -> list.forEach(b -> System.out.println(b)),
                        () -> System.out.println("Inga bokningar är tillgängliga."));
    }
}
