package org.example.app;

import org.example.repository.BookingRepository;
import org.example.repository.CustomerRepository;
import org.example.repository.VehicleRepository;
import org.example.systemIO.IIO;
import org.example.systemIO.SystemIO;

public class AppConfig {
    private final IIO IO = new SystemIO();
    private final VehicleRepository vehicleRepository = new VehicleRepository();
    private final BookingRepository bookingRepository = new BookingRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

}
