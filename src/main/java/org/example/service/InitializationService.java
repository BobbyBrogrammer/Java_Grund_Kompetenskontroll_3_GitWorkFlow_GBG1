package org.example.service;

import org.example.models.*;
import org.example.repository.BookingRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.VehicleRepository;

import java.time.LocalDate;

public class InitializationService {
    private final VehicleRepository vehicleRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;


    public InitializationService(VehicleRepository vehicleRepository, BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    public void loadInitialData() {
        // --- Kunder ---
        Customer c1 = new Customer("Anna Andersson", "0701234567", "anna@example.com");
        Customer c2 = new Customer("Bertil Berg", "0707654321", "bertil@example.com");
        Customer c3 = new Customer("Carla Carlsson", "0709876543", "carla@example.com");

        customerRepository.add(c1);
        customerRepository.add(c2);
        customerRepository.add(c3);

        // --- Fordon ---
        Vehicle v1 = new Vehicle("ABC123", "Volvo XC60", 2018);
        Vehicle v2 = new Vehicle("XYZ789", "Volkswagen Golf", 2020);
        Vehicle v3 = new Vehicle("JKL456", "Tesla Model 3", 2022);

        vehicleRepository.add(v1);
        vehicleRepository.add(v2);
        vehicleRepository.add(v3);

        // --- Bokningar ---
        Booking b1 = new Booking(v1, LocalDate.of(2025, 11, 10), 1500, c1, BookingType.SERVICE);
        Booking b2 = new Booking(v2, LocalDate.of(2025, 11, 12), 800, c2, BookingType.INSPECTION);
        Booking b3 = new RepairBooking(v3, LocalDate.of(2025, 11, 15), 0, c3, "Byta bromsar", BookingType.REPAIR);

        bookingRepository.add(b1);
        bookingRepository.add(b2);
        bookingRepository.add(b3);

    }
}
