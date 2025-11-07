package org.example.models;

public class Customer {

    private  String name;          // Kundens namn
    private  String phoneNumber;   // Telefonnummer
    private  String email;         // E-postadress (valideras i ValidationService)

    public Customer(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return name + " (" + email + ", " + phoneNumber + ")";
    }
}
