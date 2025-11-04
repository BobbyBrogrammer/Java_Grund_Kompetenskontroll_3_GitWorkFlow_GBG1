package org.example.cli;

import java.util.List;

public class SearchForBooking {
    private final InputHandler input;
    private final OutputHandler output;

    public SearchForBooking(InputHandler input, OutputHandler output) {
        this.input = input;
        this.output = output;
    }

    public void searchBooking(List<String> bookings) {
        output.printSearchForBookingTitle();
        String search = input.readRegistrationNumber();

        List<String> bookingFound = bookings.stream()
                .filter(b -> b.contains(search))
                .toList();

        if (bookingFound.isEmpty()) {
            output.printNoBookingFound();
        } else {
            output.printBookingFound();
            bookingFound.forEach(output::printSuccess);
        }
    }
}
