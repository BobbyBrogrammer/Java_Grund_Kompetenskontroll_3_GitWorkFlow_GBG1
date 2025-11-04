package org.example.validator;


import org.example.exceptions.InvalidCustomerNameException;
import org.example.exceptions.InvalidEmailAdressException;
import org.example.exceptions.InvalidPhoneNumberException;


public class CustomerValidator {


    public void validateCustomer(String name){
        if(name == null || name.trim().isBlank()){
            throw new InvalidCustomerNameException("Du måste skriva in ett giltigt namn, får inte vara tomt eller null. ");
        }
    }
    public void validatePhoneNumber(String number){
        if(!number.matches( "^\\+[0-9]+$")){
            throw new InvalidPhoneNumberException("telefonnummer ogiltigt kontrollera att det är rätt. " + number);
        }
    }
    public void validateEmail(String email){
        if(!email.matches("^[^@]+@[^@]+\\\\.(se|com)$")){
            throw new InvalidEmailAdressException(email);
        }
    }
}
