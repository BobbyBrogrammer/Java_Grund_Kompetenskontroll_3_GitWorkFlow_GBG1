package org.example.cli;

import org.example.factory.BookingFactory;
import org.example.factory.CustomerFactory;
import org.example.factory.VehicleFactory;
import org.example.models.Booking;
import org.example.models.BookingType;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.repository.BookingRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.Repository;
import org.example.repository.VehicleRepository;
import org.example.service.BookingService;
import org.example.service.CompletionService;
import org.example.service.PriceService;
import org.example.systemIO.IIO;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleUI implements BookingUI{
    private  LocalDate localDate;
    private final IIO io;
    private final Repository<Vehicle, String> vehicleRepository;
    private final Repository<Customer, String> customerRepository;
    private final Repository<Booking, Integer> bookingRepository;
    private final InputHandler input;
    private final OutputHandler output;
    private final VehicleFactory vehicleFactory;
    private final CustomerFactory customerFactory;
    private final BookingFactory bookingFactory;
    private final CompletionService completionService;
    private final PriceService priceService;
    private final BookingService bookingService;
    private final Booking booking;


    //Meny actions
    private final SearchForBooking searchAction;
    private final DeleteBooking deleteAction;
    private final UpdateBooking updateAction;

    public ConsoleUI(IIO io, InputHandler input, OutputHandler output, CompletionService completionService,
                     VehicleFactory vehicleFactory, BookingFactory bookingFactory, CustomerFactory customerFactory,
                     Repository<Vehicle, String>vehicleRepository, Repository<Customer, String> customerRepository,
                     Repository<Booking, Integer>  bookingRepository, SearchForBooking searchAction,
                     DeleteBooking deleteAction, UpdateBooking updateAction, PriceService priceService,
                     BookingService bookingService, Booking booking) {
        this.io = io;
        this.input = input;
        this.output = output;
        this.completionService = completionService;
        this.vehicleFactory = vehicleFactory;
        this.bookingFactory = bookingFactory;
        this.customerFactory = customerFactory;
        this.vehicleRepository= vehicleRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
        this.searchAction = searchAction;
        this.deleteAction = deleteAction;
        this.updateAction = updateAction;
        this.priceService = priceService;
        this.bookingService = bookingService;
        this.booking = booking;
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
        List<Booking> allBookings = bookingRepository.findAll();
        if (allBookings.isEmpty()) {
            output.printIfNoBookings();
        } else {
            for (int i = 0; i < allBookings.size(); i++) {
                io.printLine((i + 1) + ". " + allBookings.get(i));
            }
        }
    }

    public void searchBooking() {
        searchAction.searchBooking();
    }

    public void deleteBooking() {
        deleteAction.deleteBooking(bookings);
    }

    public void updateBooking() {
        updateAction.updateBooking(bookings);
    }


    public void createInspectionBooking() {
        output.printStateCreateNewBookingTitle();
        //Skapa fordon
        Vehicle vehicle = vehicleFactory.createVehicle(
                input.readRegistrationNumber(),
                input.readVehicleModel(),
                input.readYearModel());
        //Skapa kund
        Customer customer = customerFactory.createCustomer(
                input.readCustomerName(),
                input.readPhoneNumber(),
                input.readEmail());
        //Läs in datum
        LocalDate date = input.readDate();
        //Skapa bokning
        bookingService.createBooking(vehicle, date, customer, BookingType.INSPECTION);
        //Visa resultat
        if (booking != null) {
            output.printSuccess("Bokning skapad!\n" + booking);
        } else {
            output.printError("Bokningen kunde inte skapas. Kontrollera att du skrivit rätt vid inmatning.");
        }
    }

    public void createServiceBooking() {
        output.printStateCreateNewBookingTitle();
        //Skapa fordon
        Vehicle vehicle = vehicleFactory.createVehicle(
                input.readRegistrationNumber(),
                input.readVehicleModel(),
                input.readYearModel());
        //Skapa kund
        Customer customer = customerFactory.createCustomer(
                input.readCustomerName(),
                input.readPhoneNumber(),
                input.readEmail());
        //Läs in datum
        LocalDate date = input.readDate();
        //Skapa bokning
        Booking booking = bookingService.createBooking(vehicle, date, customer, BookingType.SERVICE);
        //Visa resultat
        if (booking != null) {
            output.printSuccess("Service bokning skapad!\n" + booking);
        } else {
            output.printError("Bokningen kunde inte skapas. Kontrollera att du skrivit rätt vid inmatning.");
        }

    }

    public void createRepairBooking() {
        output.printStateCreateNewBookingTitle();
        //Skapa fordon
        Vehicle vehicle = vehicleFactory.createVehicle(
                input.readRegistrationNumber(),
                input.readVehicleModel(),
                input.readYearModel());
        //Skapa kund
        Customer customer = customerFactory.createCustomer(
                input.readCustomerName(),
                input.readPhoneNumber(),
                input.readEmail());
        //Läs in datum
        LocalDate date = input.readDate();
        //Skapa bokning
        Booking booking = bookingService.createBooking(vehicle, date, customer, BookingType.REPAIR);
        //Visa resultat
        if(booking != null) {
            output.printSuccess("Reparation bokning skapad!\n" + booking + "\nPriset sätts när arbetet är klart.");
        } else {
            output.printError("Bokningen kunde inte skapas. Kontrollera att du skrivit rätt vid inmatning.");
        }
    }

    public void completeRepairBooking() {
        output.printCompleteRepairTitle();
        //Läs in boknings-ID
        output.askForBookingId();
        int bookingId = Integer.parseInt(io.readLine());

        output.askForRepairPrice();
        double repairPrice = Double.parseDouble(io.readLine());

        try {
            bookingService.completeRepairBooking(bookingId, repairPrice);
            output.printSuccess("Reparationen är nu klar och e-post skickat till kunden.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            output.printError(ex.getMessage());
        }
    }
}




