package org.example.factory;


import org.example.models.Vehicle;
import org.example.service.LoggingService;
import org.example.validator.VehicleValidator;


public class VehicleFactory {
    private final VehicleValidator validator;
    private final LoggingService logger;

    public VehicleFactory(VehicleValidator validator, LoggingService logger) {
        this.validator = validator;
        this.logger = logger;
    }

    public Vehicle createVehicle(String name, String model, int year){
        validator.validateRegistrationNumber(name);
        validator.validateModel(model);
        validator.validateYearModel(year);
        Vehicle vehicle = new Vehicle(name, model, year);
        logger.logInfo("Nytt fordon skapat: " + name + " (" + model + ", " + year + ")");
        return vehicle;
    }
}
