package org.example.repository;

import org.example.models.Booking;

import java.util.*;


public class BookingRepository implements Repository<Booking, Integer > {
    Map<Integer, Booking> bookings = new LinkedHashMap<>();

    @Override
    public void add(Booking booking){
        bookings.put(booking.getId(), booking);
    }
    @Override
    public Optional<Booking> findById(Integer id) {
        return Optional.ofNullable(bookings.get(id));
    }
    @Override
    public List<Booking> findAll(){
        return new ArrayList<>(bookings.values());
    }

    @Override
    public void remove(Integer id){
        bookings.remove(id);
    }

}

