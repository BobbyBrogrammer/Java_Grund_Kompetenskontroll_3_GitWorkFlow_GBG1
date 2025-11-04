package org.example.repository;

import org.example.models.Customer;

import java.util.*;

public class CustomerRepository implements Repository <Customer, String>{

    Map<String, Customer> customers = new LinkedHashMap<>();

    @Override
   public void add(Customer c){
       customers.put(c.getPhoneNumber(), c);
   }
    @Override
   public Optional<Customer>findById(String phoneNumber){
       return Optional.ofNullable(customers.get(phoneNumber));
   }
   @Override
   public List<Customer> findAll(){
       return new ArrayList<>(customers.values());
   }
   @Override
   public void remove(String phoneNumber){
        customers.remove(phoneNumber);
   }
}
