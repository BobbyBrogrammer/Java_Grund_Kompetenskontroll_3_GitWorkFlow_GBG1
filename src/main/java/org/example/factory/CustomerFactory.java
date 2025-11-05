package org.example.factory;

import org.example.models.Customer;
import org.example.validator.CustomerValidator;

public class CustomerFactory {
    CustomerValidator validator;


    public CustomerFactory(CustomerValidator validator) {
        this.validator = validator;
    }

    public Customer createCustomer(String name, String phoneNumber, String email){
        validator.validateCustomer(name);
        validator.validatePhoneNumber(phoneNumber);
        validator.validateEmail(email);
        return new Customer(name, phoneNumber, email);
    }
}
