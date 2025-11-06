package org.example.service;

import org.example.factory.BookingFactory;
import org.example.factory.CustomerFactory;
import org.example.factory.VehicleFactory;
import org.example.models.*;
import org.example.repository.Repository;

import java.time.LocalDate;

/**
 * InitializationService fyller systemet med exempeldata vid start.
 */
public class InitializationService {

    private final Repository<Customer, String> customerRepository;
    private final Repository<Vehicle, String> vehicleRepository;
    private final Repository<Booking, Integer> bookingRepository;

    private final CustomerFactory customerFactory;
    private final VehicleFactory vehicleFactory;
    private final BookingFactory bookingFactory;

    public InitializationService(Repository<Customer, String> customerRepository,
                                 Repository<Vehicle, String> vehicleRepository,
                                 Repository<Booking, Integer> bookingRepository,
                                 CustomerFactory customerFactory,
                                 VehicleFactory vehicleFactory,
                                 BookingFactory bookingFactory) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.bookingRepository = bookingRepository;
        this.customerFactory = customerFactory;
        this.vehicleFactory = vehicleFactory;
        this.bookingFactory = bookingFactory;
    }

    /**
     * Laddar några exempelbokningar, kunder och fordon.
     */
    public void loadInitialData() {
        System.out.println("\n--- Initierar exempeldata ---");

        // Skapa kunder
        Customer c1 = customerFactory.createCustomer("Anna Karlsson", "0701234567", "anna.karlsson@example.com");
        Customer c2 = customerFactory.createCustomer("Mohammed Ali", "0729876543", "mohammed.ali@example.com");
        Customer c3 = customerFactory.createCustomer("Lisa Pettersson", "0734567890", "lisa.p@example.com");

        customerRepository.add(c1);
        customerRepository.add(c2);
        customerRepository.add(c3);

        // Skapa fordon
        Vehicle v1 = vehicleFactory.createVehicle("ABC123", "Volvo XC60", 2019);
        Vehicle v2 = vehicleFactory.createVehicle("XYZ789", "Toyota Corolla", 2015);
        Vehicle v3 = vehicleFactory.createVehicle("MNB456", "BMW 320i", 2021);

        vehicleRepository.add(v1);
        vehicleRepository.add(v2);
        vehicleRepository.add(v3);

        // Skapa bokningar
        Booking b1 = bookingFactory.bookService(v1, LocalDate.now().minusDays(2), c1, 1800);
        Booking b2 = bookingFactory.bookInspection(v2, LocalDate.now().plusDays(3), c2);
        Booking b3 = bookingFactory.bookRepair(v3, LocalDate.now(), c3);

        bookingRepository.add(b1);
        bookingRepository.add(b2);
        bookingRepository.add(b3);

        System.out.println("✅ Exempeldata inläst: " + bookingRepository.findAll().size() + " bokningar.");
    }
}
