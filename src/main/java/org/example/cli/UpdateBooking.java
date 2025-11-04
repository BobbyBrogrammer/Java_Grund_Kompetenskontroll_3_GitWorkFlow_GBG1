package org.example.cli;

import java.util.List;

public class UpdateBooking {
    private final InputHandler input;
    private final OutputHandler output;

    public UpdateBooking(InputHandler input, OutputHandler output) {
        this.input = input;
        this.output = output;
    }

    public void updateBooking(List<String> bookings) {
        output.printUpdateBookTitle();
        String search = input.readRegistrationNumber();

        bookings.stream()
                .filter(b -> b.contains(search))
                .findFirst()
                .ifPresentOrElse(foundBooking -> {
                    output.printStateCarModel();
                    String newVehicle = input.readVehicleModel();
                    int index = bookings.indexOf(foundBooking);
                    bookings.set(index, search + " (" + newVehicle + ")");
                    output.printBookingHaveBeenUpdated();
                }, () -> output.printNoBookingFoundToUpdate());
        }
    }

