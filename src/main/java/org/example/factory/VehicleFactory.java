package org.example.factory;


import org.example.models.Vehicle;
import org.example.validator.VehicleValidator;


public class VehicleFactory {
    private final VehicleValidator validator;

    public VehicleFactory(VehicleValidator validator) {
        this.validator = validator;
    }

    public Vehicle createVehicle(String name, String model, int year){
        validator.validateRegistrationNumber(name);
        validator.validateModel(model);
        validator.validateYearModel(year);
        return new Vehicle(name, model, year);
    }
}
