package org.example.service;

import org.example.models.Booking;
import org.example.models.BookingType;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.models.Status;
import org.example.repository.BookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BookingService hanterar logiken för att skapa, visa och ta bort bokningar.
 * (Följer SRP och använder Repository för lagring)
 */
public class BookingService {

    private final BookingRepository bookingRepository;
    private final LoggingService loggingService;
    private final ValidationService validationService;
    private final PriceCalculatorService priceCalculatorService;

    // 🔹 Constructor med dependency injection
    public BookingService(BookingRepository bookingRepository,
                          LoggingService loggingService,
                          ValidationService validationService,
                          PriceCalculatorService priceCalculatorService) {
        this.bookingRepository = bookingRepository;
        this.loggingService = loggingService;
        this.validationService = validationService;
        this.priceCalculatorService = priceCalculatorService;
    }

    // --------------------------------------------------

    /**
     * Skapar en ny bokning om all data är giltig.
     */
    public void createBooking(Vehicle vehicle, LocalDate date, Customer customer, BookingType type) {
        if (!validationService.isValidDate(date)) {
            System.out.println("❌ Ogiltigt datum!");
            return;
        }

        double price = priceCalculatorService.calculatePrice(type, vehicle);

        Booking booking = new Booking(vehicle, date, price, customer, type);
        bookingRepository.addBooking(booking);
        loggingService.logInfo("✅ Ny bokning skapad: " + booking);
    }

    // --------------------------------------------------

    /**
     * Hämtar alla bokningar
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    // --------------------------------------------------

    /**
     * Filtrerar bokningar baserat på status (DONE / NOT_DONE)
     */
    public List<Booking> getBookingsByStatus(Status status) {
        return bookingRepository.getAllBookings().stream()
                .filter(b -> b.getStatus() == status)
                .collect(Collectors.toList());
    }

    // --------------------------------------------------

    /**
     * Filtrerar bokningar per kund
     */
    public List<Booking> getBookingsByCustomer(String customerName) {
        return bookingRepository.getAllBookings().stream()
                .filter(b -> b.getCustomer().getName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    // --------------------------------------------------

    /**
     * Markerar en bokning som färdig (DONE)
     */
    public void completeBooking(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking != null) {
            booking.setStatus(Status.DONE);
            loggingService.logInfo("🟢 Bokning " + bookingId + " markerad som klar.");
        } else {
            loggingService.logError("❌ Bokning med ID " + bookingId + " hittades inte!");
        }
    }

    // --------------------------------------------------

    /**
     * Tar bort en bokning
     */
    public void removeBooking(int bookingId) {
        bookingRepository.removeBooking(bookingId);
        loggingService.logInfo("🗑️ Bokning med ID " + bookingId + " har tagits bort.");
    }

    // --------------------------------------------------

    /**
     * Skriver ut alla bokningar till konsolen
     */
    public void printAllBookings() {
        List<Booking> bookings = bookingRepository.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("📭 Inga bokningar tillgängliga.");
        } else {
            bookings.forEach(System.out::println);
        }
    }
}
