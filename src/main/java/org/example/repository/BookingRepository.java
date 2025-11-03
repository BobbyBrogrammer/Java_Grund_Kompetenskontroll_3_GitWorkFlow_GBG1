package org.example.repository;

import org.example.models.Booking;

import java.util.*;

public abstract class BookingRepository implements Repository {
    Map<UUID, Booking> vehicle = new LinkedHashMap<>();
    public BookingRepository(Map<UUID, Booking> vehicle) {
        this.vehicle = vehicle;
    }
}
