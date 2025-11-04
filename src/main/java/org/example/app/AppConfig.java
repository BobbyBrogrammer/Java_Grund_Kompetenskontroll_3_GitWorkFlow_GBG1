package org.example.app;


import org.example.cli.Menu;
import org.example.cli.ConsoleUI;
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
    private final PriceService priceService = new PriceService();
    private final ValidationService validationService = new ValidationService();
    private final MailService mailService = new MailService();
    private final LoggingService loggingService = new LoggingService();
    private final CompletionService completionService = new CompletionService(priceService, validationService,mailService,loggingService);
    private final ConsoleUI ui = new ConsoleUI(IO, completionService);
    private final Menu menuRun = new Menu((SystemIO) IO, ui);
    public final Menu menuRunner(){return menuRun;}

}
