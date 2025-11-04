//package org.example.service;
//
//import org.example.models.*;
//import org.example.repository.BookingRepository;
//import org.example.repository.CustomerRepository;
//import org.example.repository.VehicleRepository;
//
//import java.time.LocalDate;
//
//public class InitializationService {
//    private final VehicleRepository vehicleRepository;
//    private final BookingRepository bookingRepository;
//    private final CustomerRepository customerRepository;
//
//
//    public InitializationService(VehicleRepository vehicleRepository, BookingRepository bookingRepository, CustomerRepository customerRepository) {
//        this.vehicleRepository = vehicleRepository;
//        this.bookingRepository = bookingRepository;
//        this.customerRepository = customerRepository;
//    }
//
//    public void loadData(String reg, String model, int yearModel, String name, String phoneNumber, String mail, Vehicle v, LocalDate localDate, Double price, Customer c, BookingType bookingType) {
//        Vehicle vehicle = new Vehicle(reg, model, yearModel);
//        Customer customer = new Customer(name, phoneNumber, mail);
//        Booking booking = new Booking(v, localDate, price, customer, bookingType);
//    }
//}
