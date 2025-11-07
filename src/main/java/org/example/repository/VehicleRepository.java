package org.example.repository;

import org.example.models.Vehicle;
import org.example.service.LoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class VehicleRepository implements Repository<Vehicle, String> {
    // Logger för att logga information till konsol eller fil
    private static final Logger log = LoggerFactory.getLogger(VehicleRepository.class);

    // Använder en LinkedHashMap för att lagra fordon med registreringsnummer som nyckel
    // LinkedHashMap behåller insättningsordningen, vilket är praktiskt om man vill visa fordon i samma ordning som de lades till.
    // Map är ett bra val här eftersom varje fordon har ett unikt registreringsnummer.
    // Det ger snabb åtkomst, uppdatering och borttagning med O(1) tidskomplexitet.
    private final Map<String, Vehicle> vehicles = new LinkedHashMap<>();

    // Loggningstjänst
    LoggingService logger;

    public VehicleRepository(LoggingService logger) {
        this.logger = logger;
    }

    @Override
    public void add(Vehicle v) {
        // Lägger till fordonet i Map med registreringsnummer som nyckel
        vehicles.put(v.getRegistrationNumber(), v);
        logger.logInfo("Bil tillagd " + v.getRegistrationNumber());
    }

    @Override
    public Optional<Vehicle> findById(String plate) {
        // Söker efter fordon via registreringsnumret (nyckeln i Map)
        // Optional används för att tydligt visa att resultatet kan saknas
        logger.logDebug("Söker efter bil med registreringsnummer " + plate);
        return Optional.ofNullable(vehicles.get(plate));
    }

    @Override
    public void remove(String plate) {
        // Tar bort fordonet från Map via registreringsnumret
        logger.logInfo("Bil borttagen med registreringsnummer " + plate);
        vehicles.remove(plate);
    }

    @Override
    public List<Vehicle> findAll() {
        // Returnerar alla fordon som en lista, skapad från Mapens värden
        // List används här för att enkelt kunna iterera och visa alla fordon
        logger.logDebug("Hämtade alla bilar som är inlagda " + vehicles.size());
        return new ArrayList<>(vehicles.values());
    }

    @Override
    public boolean update(String regNr, Vehicle updated) {
        // Uppdaterar ett fordon om det redan finns i Mapen
        if (!vehicles.containsKey(regNr)) {
            logger.logDebug("Försökte uppdatera bil som vi inte har i systemet: " + regNr);
            System.out.println("Försök att uppdatera icke-existerande vehicle: " + regNr);
            return false;
        }
        vehicles.put(regNr, updated);
        logger.logInfo("Bil uppdaterad: " + regNr);
        System.out.println("Uppdaterade vehicle med registreringsnummer: " + regNr);
        return true;
    }
}

