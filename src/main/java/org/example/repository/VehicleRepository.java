package org.example.repository;

import org.example.models.Vehicle;
import java.util.*;

public class VehicleRepository implements Repository<Vehicle, String> {
    private final Map<String, Vehicle> vehicles = new LinkedHashMap<>();


    @Override
    public void add(Vehicle v){
        vehicles.put(v.getRegistrationNumber(), v);
    }

    @Override
    public Optional<Vehicle> findById(String plate){
        return Optional.ofNullable(vehicles.get(plate));
    }

    @Override
    public void remove(String plate){
        vehicles.remove(plate);
    }

    @Override
    public List<Vehicle> findAll(){
        return new ArrayList<>(vehicles.values());
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

