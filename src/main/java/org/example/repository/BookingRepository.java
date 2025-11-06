package org.example.repository;

import org.example.models.Booking;
import org.example.service.LoggingService;

import java.util.*;


public class BookingRepository implements Repository<Booking, Integer > {
    private final Map<Integer, Booking> bookings = new LinkedHashMap<>();
    private final LoggingService logger;

    public BookingRepository(LoggingService logger) {
        this.logger = logger;
    }

    @Override
    public void add(Booking booking){
        bookings.put(booking.getId(), booking);
        logger.logInfo("La till bokning med id " + booking.getId());
    }
    @Override
    public Optional<Booking> findById(Integer id) {
        Optional<Booking> result = Optional.ofNullable(bookings.get(id));
        logger.logInfo("Hämtade bokning med id " + id + " fanns=" + result.isPresent());
        return result;
    }
    @Override
    public List<Booking> findAll(){
        List<Booking> booking = new ArrayList<>(bookings.values());
        logger.logInfo("Hämtade alla bokningar (" + booking.size() + " st)");
        return booking;
    }

    @Override
    public void remove(Integer id){
        bookings.remove(id);
        logger.logInfo("Tog bort bokning med id " + id);
    }
}
