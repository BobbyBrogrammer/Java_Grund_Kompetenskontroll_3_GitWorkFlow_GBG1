package org.example.service;



import org.example.models.Booking;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.repository.BookingRepository;
import org.example.utils.DateUtils;
import org.example.utils.EmailValidator;
import org.example.service.ValidationService;
import org.example.service.LoggingService;

import java.util.List;
import java.util.stream.Collectors;


/**
 * BookingService hanterar skapande och visning av bokningar.
 * (SRP + OCP)
 */
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ValidationService validationService;
    private final LoggingService loggingService;

    // 🔹 Konstruktor för Dependency Injection
    public BookingService(BookingRepository bookingRepository,
                          ValidationService validationService,
                          LoggingService loggingService) {
        this.bookingRepository = bookingRepository;
        this.validationService = validationService;
        this.loggingService = loggingService;
    }
    // --------------------------------------------------

    /**
     * Skapar en ny bokning om all data är giltig.
     */
    public void createBooking(Customer customer, Vehicle vehicle, String date) {
        if (!validationService.isValidDate(date)) {
            System.out.println("❌ Ogiltigt datumformat!");
            return;
        }

        Booking booking = new Booking(customer, vehicle, date);
        bookingRepository.addBooking(booking);
        loggingService.logInfo("Ny bokning skapad: " + booking);
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
     * Filtrerar bokningar per kund
     */
    public List<Booking> getBookingsByCustomer(String customerName) {
        return bookingRepository.getAllBookings().stream()
                .filter(b -> b.getCustomer().getName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    // --------------------------------------------------

    /**
     * Tar bort en bokning via ID
     */
    public void removeBooking(int id) {
        bookingRepository.removeBooking(id);
        loggingService.logInfo("Bokning med ID " + id + " togs bort.");
    }

    // --------------------------------------------------

    /**
     * Skriver ut alla bokningar
     */
    public void printAllBookings() {
        List<Booking> bookings = bookingRepository.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("📭 Inga bokningar hittades.");
        } else {
            bookings.forEach(System.out::println);
        }
    }
}

