package org.example.repository;

import org.example.models.Booking;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class BookingRepository implements Repository {
    Map<UUID, Booking> vehicle = new LinkedHashMap<>();


    public BookingRepository(Map<UUID, Booking> vehicle) {
        this.vehicle = vehicle;
    }


}
