package org.example.repository;

import org.example.models.Customer;
import org.example.service.LoggingService;

import java.util.*;

public class CustomerRepository implements Repository <Customer, String>{
    private final Map<String, Customer> customers = new LinkedHashMap<>();
    private final LoggingService logger;

    public CustomerRepository(LoggingService logger) {
        this.logger = logger;
    }

    @Override
   public void add(Customer c){
       customers.put(c.getPhoneNumber(), c);
        logger.logInfo("La till kund med namn: " + c.getName());
   }
    @Override
   public Optional<Customer>findById(String phoneNumber){
       Optional<Customer> customer = Optional.ofNullable(customers.get(phoneNumber));
       logger.logInfo("Hämtade bokning med telefonnummer " + phoneNumber);
       return customer;
   }
   @Override
   public List<Customer> findAll(){
       List<Customer> allCustomers =  new ArrayList<>(customers.values());
       logger.logInfo("Hämtade alla kunder " + allCustomers.size() + "st.");
       return allCustomers;
   }
   @Override
   public void remove(String phoneNumber){
        customers.remove(phoneNumber);
        logger.logInfo("Tog bort kund ");
   }
}
