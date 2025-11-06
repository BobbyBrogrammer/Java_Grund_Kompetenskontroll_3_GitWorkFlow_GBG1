package org.example.factory;

import org.example.models.Customer;
import org.example.service.LoggingService;
import org.example.validator.CustomerValidator;

public class CustomerFactory {
   private final CustomerValidator validator;
   private final LoggingService logger;



    public CustomerFactory(CustomerValidator validator, LoggingService logger) {
        this.validator = validator;
        this.logger = logger;
    }

    public Customer createCustomer(String name, String phoneNumber, String email){
        validator.validateCustomer(name);
        validator.validatePhoneNumber(phoneNumber);
        validator.validateEmail(email);
        Customer customer = new Customer(name, phoneNumber, email);
        logger.logInfo("Ny kund skapad: " + name + " (" + email + ", " + phoneNumber + ")");
        return customer;
    }
}
