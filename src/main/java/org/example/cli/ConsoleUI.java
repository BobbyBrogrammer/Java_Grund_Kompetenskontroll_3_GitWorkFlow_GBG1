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
    private final List<String> bookings = new ArrayList<>();
    private final PriceService priceService;
    private final BookingService bookingService;


    //Meny actions
    private final SearchForBooking searchAction;
    private final DeleteBooking deleteAction;
    private final UpdateBooking updateAction;

    public ConsoleUI(IIO io, InputHandler input, OutputHandler output, CompletionService completionService,
                     VehicleFactory vehicleFactory, BookingFactory bookingFactory, CustomerFactory customerFactory,
                     Repository<Vehicle, String>vehicleRepository, Repository<Customer, String> customerRepository,
                     Repository<Booking, Integer>  bookingRepository, SearchForBooking searchAction,
                     DeleteBooking deleteAction, UpdateBooking updateAction, PriceService priceService,
                     BookingService bookingService) {
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
         output.printStateCreateNewBookingTitle();
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

    public void createServiceBooking() {
        output.printStateCreateNewBookingTitle();
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
        double price = priceService.calculateServicePrice(yearModel);
        LocalDate localDate = LocalDate.now();
        Booking booking = bookingFactory.bookService(vehicle, localDate, customer, price);
        customerRepository.add(customer);
        vehicleRepository.add(vehicle);
        bookingRepository.add(booking);
        completionService.completeProcess(email, reg, BookingType.SERVICE, vehicle);
        output.printSuccess("Service-bokning skapad för " + reg + ". Pris: " + price + " kr");
    }

    public void createRepairBooking() {
        output.printStateCreateNewBookingTitle();
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
        Booking booking = bookingFactory.bookRepair(vehicle, localDate, customer);
        customerRepository.add(customer);
        vehicleRepository.add(vehicle);
        bookingRepository.add(booking);
        output.printSuccess("Reparation-bokning är skapad! Priset sätts efter att arbetet är klart.");
    }

    public void completeRepairBooking() {
        output.printCompleteRepairTitle();
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




