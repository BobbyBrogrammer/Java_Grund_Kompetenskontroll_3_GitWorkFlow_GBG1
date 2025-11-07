package org.example.repository;

import org.example.models.Customer;
import org.example.service.LoggingService;

import java.util.*;

public class CustomerRepository implements Repository<Customer, String> {
    // Använder en LinkedHashMap för att lagra kunder med telefonnummer som nyckel.
    // LinkedHashMap behåller insättningsordningen och gör det lätt att lista kunder i samma ordning som de lades till.
    // Map passar bra här eftersom varje kund har ett unikt telefonnummer och vi får snabb åtkomst, uppdatering och borttagning (O(1) tid).
    private final Map<String, Customer> customers = new LinkedHashMap<>();

    // Hanterar loggning av händelser
    private final LoggingService logger;

    public CustomerRepository(LoggingService logger) {
        this.logger = logger;
    }

    @Override
    public void add(Customer c) {
        // Lägger till kund i Map med telefonnummer som nyckel
        customers.put(c.getPhoneNumber(), c);
        logger.logInfo("La till kund med namn: " + c.getName());
    }

    @Override
    public Optional<Customer> findById(String phoneNumber) {
        // Hämtar kund snabbt via telefonnummer från Map
        Optional<Customer> customer = Optional.ofNullable(customers.get(phoneNumber));
        logger.logInfo("Hämtade kund med telefonnummer " + phoneNumber);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        // Returnerar alla kunder som en lista, skapad från Mapens värden
        // List gör det enkelt att iterera eller visa alla kunder i följd
        List<Customer> allCustomers = new ArrayList<>(customers.values());
        logger.logInfo("Hämtade alla kunder " + allCustomers.size() + " st.");
        return allCustomers;
    }

    @Override
    public void remove(String phoneNumber) {
        // Tar bort kund med angivet telefonnummer
        customers.remove(phoneNumber);
        logger.logInfo("Tog bort kund ");
    }

    @Override
    public boolean update(String email, Customer updated) {
        // Uppdaterar kund om den finns i Map
        if (!customers.containsKey(email)) {
            logger.logDebug("Försökte uppdatera kund som inte finns i systemet: " + email);
            System.out.println("Försök att uppdatera icke-existerande kund: " + email);
            return false;
        }
        customers.put(email, updated);
        logger.logInfo("Kund uppdaterad: " + email + " " + updated);
        System.out.println("Uppdaterade kund med email: " + email);
        return true;
    }
}