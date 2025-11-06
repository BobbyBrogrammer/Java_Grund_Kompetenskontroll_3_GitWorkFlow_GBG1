package org.example.app;


import org.example.cli.*;
import org.example.factory.BookingFactory;
import org.example.factory.CustomerFactory;
import org.example.factory.VehicleFactory;
import org.example.menus.BookingSelectionMenu;
import org.example.menus.Menu;
import org.example.models.Booking;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.repository.BookingRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.Repository;
import org.example.repository.VehicleRepository;
import org.example.service.*;
import org.example.systemIO.IIO;
import org.example.systemIO.SystemIO;
import org.example.validator.CustomerValidator;
import org.example.validator.PriceValidator;
import org.example.validator.VehicleValidator;

import javax.xml.validation.Validator;

public class AppConfig {

    private final IIO IO = new SystemIO();
    private final LoggingService loggingService = new LoggingService();
    private final Repository<Vehicle, String> vehicleRepository = new VehicleRepository();
    private final Repository<Booking, Integer> bookingRepository = new BookingRepository();
    private final Repository<Customer, String> customerRepository = new CustomerRepository();
    private final VehicleValidator vehicleValidator = new VehicleValidator();
    private final CustomerValidator customerValidator = new CustomerValidator();
    private final PriceValidator priceValidator = new PriceValidator();
    private final VehicleFactory vehicleFactory = new VehicleFactory(vehicleValidator, loggingService);
    private final CustomerFactory customerFactory = new CustomerFactory(customerValidator, loggingService);
    private final PriceService priceService = new PriceService(priceValidator);
    private final BookingFactory bookingFactory = new BookingFactory(priceService, loggingService);
    private final ValidationService validationService = new ValidationService();
    private final OutputHandler output = new OutputHandler(IO);
    private final InputHandler input = new InputHandler(output, IO, validationService);
    private final SearchForBooking searchAction = new SearchForBooking(input, output, bookingRepository, loggingService);
    private final DeleteBooking deleteAction = new DeleteBooking(output, bookingRepository, IO, loggingService);
    private final UpdateBooking updateAction = new UpdateBooking(input, output, bookingRepository, IO, loggingService);
    private final ShowAllBookings showAllBookings = new ShowAllBookings(output, bookingRepository, IO);
    private final MailService mailService = new MailService();
    private final CompletionService completionService = new CompletionService(priceService, validationService,mailService,loggingService);
    private final BookingService bookingService = new BookingService(bookingRepository, vehicleRepository, customerRepository, loggingService, validationService, priceService, completionService);
    private final ConsoleUI ui = new ConsoleUI(IO, input, output, completionService, vehicleFactory, bookingFactory, customerFactory, vehicleRepository , customerRepository, bookingRepository, searchAction, deleteAction, updateAction, priceService, bookingService, showAllBookings);
    private final BookingSelectionMenu bookingSelectionMenu = new BookingSelectionMenu(IO, output, ui);
    private final Menu menuRun = new Menu(IO, input, output, ui, bookingSelectionMenu);
    private final InitializationService initializationService =
            new InitializationService(customerRepository, vehicleRepository, bookingRepository,
                    customerFactory, vehicleFactory, bookingFactory);

    public InitializationService getInitializationService() {
        return initializationService;
    }
    public final Menu menuRunner(){return menuRun;}
}
