package org.example.validator;


import org.example.exceptions.InvalidCustomerNameException;
import org.example.exceptions.InvalidEmailAdressException;
import org.example.exceptions.InvalidPhoneNumberException;


public class CustomerValidator implements Validator<Object> {


    public boolean validateCustomer(String name){
        if(name == null || name.trim().isBlank()){
            throw new InvalidCustomerNameException("Du måste skriva in ett giltigt namn, får inte vara tomt eller null. ");
        }
        return true;
    }
    public boolean validatePhoneNumber(String number){
        if(!number.matches( "^\\+[0-9]+$")){
            throw new InvalidPhoneNumberException("telefonnummer ogiltigt kontrollera att det är rätt. " + number);
        }
        return true;
    }
    public boolean validateEmail(String email){
        if(!email.matches("^[^@]+@[^@]+\\\\.(se|com)$")){
            throw new InvalidEmailAdressException(email);
        }
        return true;
    }
    @Override
    public boolean isValid(Object customer){
        return true;
    }
}
