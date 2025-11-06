package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;
import org.example.service.LoggingService;

import java.util.List;

public class SearchForBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final Repository<Booking, Integer> bookingRepository;
    private final LoggingService logger;

    public SearchForBooking(InputHandler input, OutputHandler output, Repository<Booking, Integer> bookingRepository, LoggingService logger) {
        this.input = input;
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.logger = logger;
    }

    public void searchBooking() {
        output.printSearchForBookingTitle();
        String search = input.readRegistrationNumber();

        List<Booking> bookingFound = bookingRepository.findAll().stream()
                .filter(b -> b.getVehicle().getRegistrationNumber().equalsIgnoreCase(search))
                .toList();

        if (bookingFound.isEmpty()) {
            output.printNoBookingFound();
        } else {
            output.printBookingFound();
            bookingFound.forEach(b -> output.printBookingDetails(b.toString()));

        }
    }
}
