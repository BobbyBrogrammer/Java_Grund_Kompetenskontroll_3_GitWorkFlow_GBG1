package org.example.cli;

import org.example.service.CompletionService;
import org.example.systemIO.IIO;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUI {
    private final IIO io;
    private final InputHandler input;
    private final OutputHandler output;

    private final CompletionService completionService;
    private final List<String> bookings = new ArrayList<>();

    //Meny actions
    private final SearchForBooking searchAction;
    private final DeleteBooking deleteAction;
    private final UpdateBooking updateAction;

    public ConsoleUI(IIO io, InputHandler input, OutputHandler output, CompletionService completionService) {
        this.io = io;
        this.input = input;
        this.output = output;
        this.completionService = completionService;
        this.searchAction = new SearchForBooking(input, output);
        this.deleteAction = new DeleteBooking(input, output);
        this.updateAction = new UpdateBooking(input, output);
    }
    public void createBooking() {
        output.printStateCreateNewBookingTitle();
        output.printStateCustomerName();
        String name = input.readCustomerName();

        output.printStateCustomerEmail();
        String email = input.readEmail();

        output.printStateCustomerPhoneNumber();
        String phone = input.readPhoneNumber();

        output.printStateCarRegNumber();
        String reg = input.readRegistrationNumber();

        output.printStateCarModel();
        String model = input.readVehicleModel();

        output.printStateYearModel();
        int year = input.readYearModel();

        output.printBookingSuccess();
    }
    public void showAllBookings() {
        output.printShowAllBookingsTitle();
        if (bookings.isEmpty()) {
            output.printIfNoBookings();
        } else {
            for (int i = 0; i < bookings.size(); i++) {
                io.printLine((i + 1) + ". " + bookings.get(i));
            }
        }
    }
    public void searchBooking() {searchAction.searchBooking(bookings);}
    public void deleteBooking() {deleteAction.deleteBooking(bookings);}
    public void updateBooking() {updateAction.updateBooking(bookings);}
}
