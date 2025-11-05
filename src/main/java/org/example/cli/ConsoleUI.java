package org.example.cli;

import org.example.factory.BookingFactory;
import org.example.factory.CustomerFactory;
import org.example.factory.VehicleFactory;
import org.example.models.Booking;
import org.example.models.BookingType;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.repository.*;
import org.example.repository.Repository;
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
    private final SearchForBooking searchForBooking;
    private final DeleteBooking deleteBooking;
    private final UpdateBooking updateBooking;
    private final ShowAllBookings showAllBookings;

    public ConsoleUI(IIO io, InputHandler input, OutputHandler output, CompletionService completionService,
                     VehicleFactory vehicleFactory, BookingFactory bookingFactory, CustomerFactory customerFactory,
                     Repository<Vehicle, String>vehicleRepository, Repository<Customer, String> customerRepository,
                     Repository<Booking, Integer>  bookingRepository, SearchForBooking searchForBooking,
                     DeleteBooking deleteBooking, UpdateBooking updateBooking, PriceService priceService,
                     BookingService bookingService, Booking booking, ShowAllBookings showAllBookings) {
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
        this.searchForBooking = searchForBooking;
        this.deleteBooking = deleteBooking;
        this.updateBooking = updateBooking;
        this.priceService = priceService;
        this.showAllBookings = showAllBookings;
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


    public void showAllBookings(){showAllBookings.showAllBookings();}
    public void searchBooking() {searchForBooking.searchBooking();}
    public void deleteBooking() {deleteBooking.deleteBooking();}
    public void updateBooking() {updateBooking.updateBooking();}


    public void createInspectionBooking() {
        output.printStateCreateNewBookingTitle();
        //Skapa fordon
        Vehicle vehicle = vehicleFactory.createVehicle(
        input.readRegistrationNumber(),
        input.readVehicleModel(),
        input.readYearModel());
        vehicleRepository.add(vehicle);
        //Skapa kund
        Customer customer = customerFactory.createCustomer(
        input.readCustomerName(),
        input.readPhoneNumber(),
        input.readEmail());
        customerRepository.add(customer);
        //Läs in datum
        LocalDate date = input.readDate();
        //Skapa bokning
        Booking booking = bookingFactory.bookInspection(vehicle, localDate, customer);
        bookingRepository.add(booking);
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
        vehicleRepository.add(vehicle);
        //Skapa kund
        Customer customer = customerFactory.createCustomer(
                input.readCustomerName(),
                input.readPhoneNumber(),
                input.readEmail());
        customerRepository.add(customer);
        //Läs in datum
        LocalDate date = input.readDate();
        //Skapa bokning
        Booking booking = bookingService.createBooking(vehicle, date, customer, BookingType.SERVICE);
        bookingRepository.add(booking);
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
        vehicleRepository.add(vehicle);
        //Skapa kund
        Customer customer = customerFactory.createCustomer(
                input.readCustomerName(),
                input.readPhoneNumber(),
                input.readEmail());
        customerRepository.add(customer);
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
        //Läs in boknings-ID
        output.askForBookingId();
        int bookingId;
        try {
            bookingId = Integer.parseInt(io.readLine().trim());
        } catch (NumberFormatException ex) {
            output.printError("Ogiltigt ID. Du måste ange siffror.");
            return;
        }
        //Läs in mekanikern pris
        output.askForRepairPrice();
        double repairPrice;
        try {
            repairPrice = Double.parseDouble(io.readLine().trim());
        } catch (NumberFormatException ex) {
            output.printError("Ogiltigt pris, tar endast emot siffror.");
            return;
        }
        //Markera reparation som klar
        try {
            Booking updated = bookingService.completeRepairBooking(bookingId, repairPrice);

            if (updated != null) {
                output.printSuccess("Reparationen är markerad som klar!\n" + updated);
            } else {
                output.printError("Ingen bokning hittades med ID: " + bookingId);
            }
        } catch (IllegalArgumentException | IllegalStateException ex) {
            output.printError("Fel: " + ex.getMessage());
        }
    }
}




