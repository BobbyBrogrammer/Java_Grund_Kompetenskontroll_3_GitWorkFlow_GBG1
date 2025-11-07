package org.example.repository;

import org.example.models.Booking;
import org.example.service.LoggingService;

import java.util.*;


public class BookingRepository implements Repository<Booking, Integer> {
    // Använder en LinkedHashMap för att lagra bokningar med ID som nyckel
    // Val av LinkedHashMap gör att ordningen på insättning bevaras,
    // samtidigt som vi får snabba operationer för att lägga till, hämta och ta bort (O(1) tid)
    private final Map<Integer, Booking> bookings = new LinkedHashMap<>();

    // Loggning av händelser
    private final LoggingService logger;

    public BookingRepository(LoggingService logger) {
        this.logger = logger;
    }

    @Override
    public void add(Booking booking) {
        // Lägger till ny bokning i Map – ID används som unik nyckel
        bookings.put(booking.getId(), booking);
        logger.logInfo("La till bokning med id " + booking.getId());
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        // Hämtar bokning snabbt via nyckeln (ID) i Map
        Optional<Booking> result = Optional.ofNullable(bookings.get(id));
        logger.logInfo("Hämtade bokning med id " + id + " fanns=" + result.isPresent());
        return result;
    }

    @Override
    public List<Booking> findAll() {
        // Returnerar alla bokningar som en lista
        // Skapas från Mapens värden, vilket gör det enkelt att iterera över alla
        List<Booking> booking = new ArrayList<>(bookings.values());
        logger.logInfo("Hämtade alla bokningar (" + booking.size() + " st)");
        return booking;
    }

    @Override
    public void remove(Integer id) {
        // Tar bort bokning med angivet ID
        bookings.remove(id);
        logger.logInfo("Tog bort bokning med id " + id);
    }

    @Override
    public boolean update(Integer id, Booking updated) {
        // Uppdaterar en bokning om den finns i Mapen
        if (!bookings.containsKey(id)) {
            logger.logWarn("Försök att uppdatera icke-existerande booking id " + id);
            return false;
        }
        bookings.put(id, updated);
        logger.logInfo("Uppdaterade booking id " + id);
        return true;
    }
}

