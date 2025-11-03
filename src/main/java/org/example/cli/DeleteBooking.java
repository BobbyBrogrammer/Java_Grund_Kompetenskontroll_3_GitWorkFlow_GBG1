package org.example.cli;

import java.util.List;

public class DeleteBooking {
    private final InputHandler input;
    private final OutputHandler output;

    public DeleteBooking(InputHandler input, OutputHandler output) {
        this.input = input;
        this.output = output;
    }

    public void deleteBooking(List<String> bookings) {
        output.printRemoveBooking();
        String search = input.readRegistrationNumber();
        boolean removed = bookings.removeIf(b -> b.contains(search));

        if (removed) {
            output.printBookingWasRemoved();
        } else {
            output.printNoBookingFoundToRemove();
        }
    }
}
