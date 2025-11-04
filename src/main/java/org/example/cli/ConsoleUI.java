package org.example.cli;

import org.example.factory.BookingFactory;
import org.example.factory.CustomerFactory;
import org.example.factory.VehicleFactory;
import org.example.models.Booking;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.repository.BookingRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.Repository;
import org.example.repository.VehicleRepository;
import org.example.service.CompletionService;
import org.example.systemIO.IIO;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleUI {
    private final LocalDate localDate;
    private final IIO io;
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final InputHandler input;
    private final OutputHandler output;
    private final VehicleFactory vehicleFactory;
    private final CustomerFactory customerFactory;
    private final BookingFactory bookingFactory;
    private final CompletionService completionService;
    private final List<String> bookings = new ArrayList<>();


    //Meny actions
    private final SearchForBooking searchAction;
    private final DeleteBooking deleteAction;
    private final UpdateBooking updateAction;

    public ConsoleUI(IIO io, InputHandler input, OutputHandler output, CompletionService completionService, VehicleFactory vehicleFactory, BookingFactory bookingFactory, CustomerFactory customerFactory, VehicleRepository vehicleRepository, CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.io = io;
        this.input = input;
        this.output = output;
        this.bookingFactory = bookingFactory;
        this.customerFactory = customerFactory;
        this.vehicleRepository= vehicleRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.completionService = new CompletionService();
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

    public void searchBooking() {
        searchAction.searchBooking(bookings);
    }

    public void deleteBooking() {
        deleteAction.deleteBooking(bookings);
    }

    public void updateBooking() {
        updateAction.updateBooking(bookings);
    }


    public void createInspectionBooking(){
        output.askForRegistrationNumber();
         String reg = io.readLine();
         output.askForModel();
         String model = io.readLine();
         output.askForYearModel();
         int yearModel = Integer.parseInt(io.readLine());
         Vehicle vehicle = vehicleFactory.createVehicle(reg, model, yearModel);
         output.askForName();
         String name = io.readLine();
         output.askForPhoneNumber();
         String phoneNumber = io.readLine();
         output.askForEmail();
         String email = io.readLine();
         Customer customer = customerFactory.createCustomer(name, phoneNumber, email);
          Booking book =  bookingFactory.bookInspection(vehicle, localDate, customer);
         customerRepository.add(customer);
         vehicleRepository.add(vehicle);
         bookingRepository.add(book);
    }
}




