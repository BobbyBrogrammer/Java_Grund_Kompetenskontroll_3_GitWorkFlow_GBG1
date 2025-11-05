package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;

import java.util.List;

public class SearchForBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final Repository<Booking, Integer> bookingRepository;

    public SearchForBooking(InputHandler input, OutputHandler output, Repository<Booking, Integer> bookingRepository) {
        this.input = input;
        this.output = output;
        this.bookingRepository = bookingRepository;
    }

    public void searchBooking(List<String> bookings) {
        output.printSearchForBookingTitle();
        String search = input.readRegistrationNumber();

        List<String> bookingFound = bookingRepository.findAll().stream()
                .filter(b -> b.getVehicle().getRegistrationNumber().equalsIgnoreCase())
                .toList();

        if (bookingFound.isEmpty()) {
            output.printNoBookingFound();
        } else {
            output.printBookingFound();
            bookingFound.forEach(output::printSuccess);
        }
    }
}
