package org.example.repository;

import org.example.models.Vehicle;
import org.example.service.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class VehicleRepository implements Repository<Vehicle, String> {
    private static final Logger log = LoggerFactory.getLogger(VehicleRepository.class);
    private final Map<String, Vehicle> vehicles = new LinkedHashMap<>();
    LoggingService logger;


    public VehicleRepository(LoggingService logger) {
        this.logger = logger;
    }

    @Override
    public void add(Vehicle v){
        vehicles.put(v.getRegistrationNumber(), v);
        logger.logInfo("Bil tillagd " + v.getRegistrationNumber());
    }


    @Override
    public Optional<Vehicle> findById(String plate){
        logger.logDebug("söker efter bil med registreringsnummer " + plate);
        return Optional.ofNullable(vehicles.get(plate));
    }

    @Override
    public void remove(String plate){
        logger.logInfo("Bil borttagen med registreringsnummer " + plate);
        vehicles.remove(plate);
    }

    @Override
    public List<Vehicle> findAll(){
        logger.logDebug("Hämtade alla bilar som är inlagda " + vehicles.size());
        return new ArrayList<>(vehicles.values());
    }

    @Override
    public boolean update(String regNr, Vehicle updated) {
        if (!vehicles.containsKey(regNr)) {
            logger.logDebug("försökte uppdatera bil som vi inte har i systemet: " + regNr);
            System.out.println("Försök att uppdatera icke-existerande vehicle: " + regNr);
            return false;
        }
        vehicles.put(regNr, updated);
        logger.logInfo("bil uppdaterad: " + regNr);
        System.out.println("Uppdaterade vehicle med registreringsnummer: " + regNr);
        return true;
    }







}

