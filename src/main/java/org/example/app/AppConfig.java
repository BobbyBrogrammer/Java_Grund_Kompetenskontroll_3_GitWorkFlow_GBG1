package org.example.app;


import org.example.cli.*;
import org.example.factory.BookingFactory;
import org.example.factory.CustomerFactory;
import org.example.factory.VehicleFactory;
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

public class AppConfig {

    private final IIO IO = new SystemIO();
    private final Repository<Vehicle, String> vehicleRepository = new VehicleRepository();
    private final Repository<Booking, Integer> bookingRepository = new BookingRepository();
    private final Repository<Customer, String> customerRepository = new CustomerRepository();
    private final VehicleFactory vehicleFactory = new VehicleFactory();
    private final CustomerFactory customerFactory = new CustomerFactory();
    private final PriceService priceService = new PriceService();
    private final BookingFactory bookingFactory = new BookingFactory(priceService);
    private final ValidationService validationService = new ValidationService();
    private final OutputHandler output = new OutputHandler(IO);
    private final InputHandler input = new InputHandler(output, IO, validationService);
    private final SearchForBooking searchAction = new SearchForBooking(input, output);
    private final DeleteBooking deleteAction = new DeleteBooking(input, output);
    private final UpdateBooking updateAction = new UpdateBooking(input, output);
    private final MailService mailService = new MailService();
    private final LoggingService loggingService = new LoggingService();
    private final BookingService bookingService = new BookingService(bookingRepository, loggingService, validationService, priceService, mailService);
    private final CompletionService completionService = new CompletionService(priceService, validationService,mailService,loggingService);
    private final ConsoleUI ui = new ConsoleUI(IO, input, output, completionService, vehicleFactory, bookingFactory,customerFactory, vehicleRepository , customerRepository, bookingRepository, searchAction, deleteAction, updateAction, priceService, bookingService);
    private final Menu menuRun = new Menu(IO, input, output, ui);
    public final Menu menuRunner(){return menuRun;}
}
