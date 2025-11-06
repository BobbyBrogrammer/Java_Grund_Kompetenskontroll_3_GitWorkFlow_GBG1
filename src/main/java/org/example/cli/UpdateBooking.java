package org.example.cli;

import org.example.models.Booking;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.repository.Repository;
import org.example.service.CompletionService;
import org.example.service.LoggingService;
import org.example.systemIO.IIO;
import  org.example.models.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UpdateBooking {
    private final InputHandler input;
    private final OutputHandler output;
    private final Repository<Booking, Integer> bookingRepository;
    private final IIO io;
    private final LoggingService logger;
    private final CompletionService completionService;

    public UpdateBooking(InputHandler input, OutputHandler output, Repository<Booking, Integer> bookingRepository, IIO io, LoggingService logger, CompletionService completionService) {
        this.input = input;
        this.output = output;
        this.bookingRepository = bookingRepository;
        this.io = io;
        this.logger = logger;
        this.completionService = completionService;
    }

    public void updateBooking() {
        output.printShowAllBookingsTitle();
        List<Booking> allBookings = bookingRepository.findAll();

        if (allBookings.isEmpty()) {
            output.printIfNoBookings();
            return;
        }
        //Visa alla bokningar
        allBookings.stream()
                .sorted((a, b) -> Integer.compare(a.getId(), b.getId()))
                .forEach(b -> io.printLine(b.toString()));
        int bookingId = input.readBookingId();
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            output.printNoBookingFoundToUpdate();
            return;
        }
        Booking booking = optionalBooking.get();
        Customer customer = booking.getCustomer();
        Vehicle vehicle = booking.getVehicle();

        //Läs menyval
        String choice = input.readUpdateChoice();
        if (choice.equals("9")) {
            output.printBackSpaceChoice();
            return;
        }
        if (choice.equals("10")) {
            output.printExitProgram();
            System.exit(0);
        }

        Map<String, Runnable> actions = Map.ofEntries(
                Map.entry("1", () -> customer.setName(input.readCustomerName())),
                Map.entry("2", () -> customer.setPhoneNumber(input.readPhoneNumber())),
                Map.entry("3", () -> customer.setEmail(input.readEmail())),
                Map.entry("4", () -> vehicle.setModel(input.readVehicleModel())),
                Map.entry("5", () -> vehicle.setRegistrationNumber(input.readRegistrationNumber())),
                Map.entry("6", () -> vehicle.setYearModel(input.readYearModel())),
                Map.entry("7", () -> booking.setDate(input.readDate())),
                Map.entry("8", () -> {
                    Status newStatus = input.readBookingStatus();
                    booking.setStatus(newStatus);

                    if (newStatus == Status.DONE) {
                        // när fordonet är klart → skicka mejl
                        completionService.notifyVehicleReady(booking);
                    }
                })
        );
        Runnable action = actions.get(choice);

        if (action == null) {
            output.printUnkownChoice();
            return;
        }

        action.run();

        output.printBookingHaveBeenUpdated(booking);

        if ("8".equals(choice) && booking.getStatus() == Status.DONE) {
            completionService.notifyVehicleReady(booking);
        }
    }
}