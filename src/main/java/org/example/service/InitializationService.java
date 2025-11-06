package org.example.service;

import org.example.factory.BookingFactory;
import org.example.factory.CustomerFactory;
import org.example.factory.VehicleFactory;
import org.example.models.Booking;
import org.example.models.BookingType;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;



public class InitializationService {

    private static final Logger log = LoggerFactory.getLogger(InitializationService.class);

    private final Repository<Customer, String> customerRepository;
    private final Repository<Vehicle, String>  vehicleRepository;
    private final Repository<Booking, Integer> bookingRepository;

    private final CustomerFactory customerFactory;
    private final VehicleFactory  vehicleFactory;
    private final BookingFactory  bookingFactory;
    private final PriceService    priceService;

    public InitializationService(Repository<Customer, String> customerRepository,
                                 Repository<Vehicle, String>  vehicleRepository,
                                 Repository<Booking, Integer> bookingRepository,
                                 CustomerFactory customerFactory,
                                 VehicleFactory  vehicleFactory,
                                 BookingFactory  bookingFactory,
                                 PriceService    priceService) {
        this.customerRepository = customerRepository;
        this.vehicleRepository  = vehicleRepository;
        this.bookingRepository  = bookingRepository;
        this.customerFactory    = customerFactory;
        this.vehicleFactory     = vehicleFactory;
        this.bookingFactory     = bookingFactory;
        this.priceService       = priceService;
    }

    public void loadInitialData() {
        log.info("\n--- Initierar exempeldata ---");

        // 1) Skapa kunder
        Customer c1 = customerFactory.createCustomer("Anna Karlsson",    "070 123 45 67", "anna.karlsson@example.com");
        Customer c2 = customerFactory.createCustomer("Mohammed Ali",     "072 987 65 43", "mohammed.ali@example.com");
        Customer c3 = customerFactory.createCustomer("Lisa Pettersson",  "073 456 78 90", "lisa.p@example.com");

        customerRepository.add(c1);  log.info("Ny kund skapad: {}", c1);
        customerRepository.add(c2);  log.info("Ny kund skapad: {}", c2);
        customerRepository.add(c3);  log.info("Ny kund skapad: {}", c3);


        Vehicle v1 = vehicleFactory.createVehicle("ABC123", "Volvo XC60",   2019);
        Vehicle v2 = vehicleFactory.createVehicle("XYZ784", "Toyota Corolla", 2015);
        Vehicle v3 = vehicleFactory.createVehicle("MNB455", "BMW 320i",     2021);

        vehicleRepository.add(v1);   log.info("Nytt fordon skapadt: {}", v1);
        vehicleRepository.add(v2);   log.info("Nytt fordon skapadt: {}", v2);
        vehicleRepository.add(v3);   log.info("Nytt fordon skapadt: {}", v3);


        LocalDate d1 = LocalDate.now().plusDays(1);
        LocalDate d2 = LocalDate.now().plusDays(3);
        LocalDate d3 = LocalDate.now().plusDays(5);


        double servicePriceV1 = priceService.calculateServicePrice(v1.getYearModel());
        Booking b1 = bookingFactory.bookService(v1, d1, c1, servicePriceV1);
        bookingRepository.add(b1);
        log.info("Skapade SERVICE-bokning: {}", b1);

        Booking b2 = bookingFactory.bookInspection(v2, d2, c2);
        bookingRepository.add(b2);
        log.info("Skapade BESIKTNING-bokning: {}", b2);


        Booking b3 = bookingFactory.bookRepair(v3, d3, c3);
        bookingRepository.add(b3);
        log.info("Skapade REPARATIONS-bokning: {}", b3);

        log.info("Exempeldata inläst: {} bokningar.", bookingRepository.findAll().size());
    }
}
