package org.example.models;

public class Vehicle {
    private String registrationNumber;  // Svenskt format, valideras senare
    private String model;                // Ex: Mercedes Benz, Volkswagen Golf
    private int yearModel;               // Ex: 2018

    public Vehicle(String registrationNumber, String model, int yearModel) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.yearModel = yearModel;
    }

    // Getters och setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }


    public void setModel(String model) {
        this.model = model;
    }

    public int getYearModel() {
        return yearModel;
    }

    public void setYearModel(int yearModel) {
        this.yearModel = yearModel;
    }

    @Override
    public String toString() {
        return model + " (" + registrationNumber + ", " + yearModel + ")";
    }
}

