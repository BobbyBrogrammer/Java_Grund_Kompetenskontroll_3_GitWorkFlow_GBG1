package org.example.cli;

import org.example.models.Booking;
import org.example.repository.Repository;
import org.example.systemIO.IIO;

import java.util.Comparator;
import java.util.List;

public class ShowAllBookings {
    private final OutputHandler output;
    private final Repository<Booking, Integer> bookingRepository;
    private final IIO io;

    public ShowAllBookings(OutputHandler output, Repository<Booking, Integer> bookingRepository, IIO io) {
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.io = io;
    }

    public void showAllBookings() {
        output.printShowAllBookingsTitle();
        List<Booking> allBookings = bookingRepository.findAll();

        if (allBookings.isEmpty()) {
            output.printIfNoBookings();
            return;
        }
        output.printSortOptions();
        String sortChoice = io.readLine().trim();

        Comparator<Booking> comparator = switch (sortChoice) {
            case "1" -> Comparator.comparing(b -> b.getId());
            case "2" -> Comparator.comparing(b -> b.getDate());
            case "3" -> Comparator.comparing(b -> b.getStatus());
            default -> Comparator.comparing(b -> b.getId());
        };
        allBookings.stream()
                .sorted(comparator)
                .forEach(b -> io.printLine(b.toString()));
    }

    public int readBookingId() {
        while (true) {
            output.askForBookingId();
            try {
                int id = Integer.parseInt(io.readLine().trim());
                if (id > 0) {
                    return id;
                }
                output.printWrongNumberInput();
            } catch (NumberFormatException e) {
                output.printWrongNumberInput();
            }
        }
    }
}
