package org.example.repository;

import org.example.models.Vehicle;
import org.example.service.LoggingService;

import java.util.*;

public class VehicleRepository implements Repository<Vehicle, String> {
    private final Map<String, Vehicle> vehicles = new LinkedHashMap<>();
    private final LoggingService logger;

    public VehicleRepository(LoggingService logger) {
        this.logger = logger;
    }

    @Override
    public void add(Vehicle v){
        vehicles.put(v.getRegistrationNumber(), v);
        logger.logInfo("Lade till fordon med reg.nr: " + v.getRegistrationNumber());
    }

    @Override
    public Optional<Vehicle> findById(String plate){
        Optional<Vehicle>allVehicles = Optional.ofNullable(vehicles.get(plate));
        logger.logInfo("Hämtade alla fordon (" + vehicles.size() + " st).");
        return allVehicles;
    }

    @Override
    public void remove(String plate){
        vehicles.remove(plate);
        logger.logInfo("Tog bort fordon med reg.nr: " + plate);
    }

    @Override
    public List<Vehicle> findAll(){
        List<Vehicle> allVehicles = new ArrayList<>(vehicles.values());
        logger.logInfo("Hämtade alla fordon (" + vehicles.size() + " st).");
        return allVehicles;
    }

    public boolean exists(String plate){
        return vehicles.containsKey(plate);
    }

    public List<Vehicle> findByYear(int year){
        return vehicles.values().stream()
                .filter(v -> v.getYearModel() == year)
                .toList();
    }

}

