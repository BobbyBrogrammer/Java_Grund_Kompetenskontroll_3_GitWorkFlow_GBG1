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
}
