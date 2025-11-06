package org.example.service;

import org.example.models.Booking;
import org.example.models.BookingType;
import org.example.models.Customer;
import org.example.models.Vehicle;
import org.example.models.Status;
import org.example.repository.BookingRepository;
import org.example.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * BookingService hanterar logiken för att skapa, visa och ta bort bokningar.
 * (Följer SRP och använder Repository för lagring)
 */
public class BookingService {

    private final Repository<Booking, Integer> bookingRepository;
    private final Repository<Vehicle, String> vehicleRepository;
    private final Repository<Customer, String> customerRepository;
    private final LoggingService loggingService;
    private final ValidationService validationService;
    private final PriceService priceService;
    private final CompletionService completionService;

    //Constructor med dependency injection
    public BookingService(Repository<Booking, Integer> bookingRepository, Repository<Vehicle,
                                  String> vehicleRepository, Repository<Customer, String> customerRepository,
                          LoggingService loggingService, ValidationService validationService,
                          PriceService priceService, CompletionService completionService) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.loggingService = loggingService;
        this.validationService = validationService;
        this.priceService = priceService;
        this.completionService = completionService;
    }

    // --------------------------------------------------

    public Booking createBooking(Vehicle vehicle, LocalDate date, Customer customer, BookingType bookingType) {
        // Validera datum
        if (!validationService.isValidDate(date)) {
            loggingService.logError("Ogiltigt datum vid bokning: " + date);
            throw new IllegalArgumentException("Datumet är ogiltigt: " + date);
        }

        // Beräkna pris
        double price;
        if (bookingType == BookingType.REPAIR) {
            price = 0.0; // slutpris sätts senare
        } else {
            price = priceService.calculatePrice(bookingType, vehicle);
        }

        // Skapa bokning
        Booking booking = new Booking(vehicle, date, price, customer, bookingType);

        // Flagga flexibelt pris för reparationer
        if (bookingType == BookingType.REPAIR) {
            booking.setFlexiblePrice(true);
            booking.setFinalPrice(0.0);
        } else {
            booking.setFlexiblePrice(false);
            booking.setFinalPrice(price);
        }
            //Spara i repositorys
            customerRepository.add(customer);
            vehicleRepository.add(vehicle);
            bookingRepository.add(booking);
            //Slutför processen
            completionService.completeProcess(customer.getEmail(), vehicle.getRegistrationNumber(), bookingType, vehicle);
            //Loggar skapandet av bokningen
            loggingService.logInfo("✅ Ny bokning skapad: " + booking);
            return booking;
        }

        public void createRepairBooking(Vehicle vehicle, LocalDate date, Customer customer) {

            createBooking(vehicle, date, customer, BookingType.REPAIR);
        }

        // --------------------------------------------------

        /**
         * Hämtar alla bokningar
         */
        public List<Booking> getAllBookings () {
            return bookingRepository.findAll();
        }

        // --------------------------------------------------

        /**
         * Filtrerar bokningar baserat på status (DONE / NOT_DONE)
         */
        public List<Booking> getBookingsByStatus (Status status){
            return bookingRepository.findAll().stream()
                    .filter(b -> b.getStatus() == status)
                    .collect(Collectors.toList());
        }

        // --------------------------------------------------

        /**
         * Filtrerar bokningar per kund
         */
        public List<Booking> getBookingsByCustomer (String customerName){
            return bookingRepository.findAll().stream()
                    .filter(b -> b.getCustomer().getName().equalsIgnoreCase(customerName))
                    .collect(Collectors.toList());
        }

        // --------------------------------------------------
        /**
         * Lista reparationer med flexibelt pris där slutpris inte satts ännu.
         */
        public List<Booking> getOpenFlexibleRepairs () {
            return bookingRepository.findAll().stream()
                    .filter(b -> b.getBookingType() == BookingType.REPAIR)
                    .filter(Booking::isFlexiblePrice)
                    .filter(b -> b.getFinalPrice() <= 0.0)
                    .collect(Collectors.toList());
        }

        /**
         * Markerar en bokning som färdig (DONE)
         */
        public String completeBooking(int bookingId, Double finalPriceIfRepair) {
            Optional<Booking> opt = bookingRepository.findById(bookingId);
            if (opt.isEmpty()) {
                String msg = "❌ Bokning med ID: " + bookingId + " hittades inte.";
                loggingService.logError(msg);
                return msg;
            }

            Booking booking = opt.get();

            try {
                if (booking.getBookingType() == BookingType.REPAIR) {
                    booking.complete(finalPriceIfRepair); // kräver pris
                } else {
                    booking.complete(null);               // sätter finalPrice = price
                }

                // spara uppdateringen
                bookingRepository.update(booking.getId(), booking);

                String doneMsg = (booking.getBookingType() == BookingType.REPAIR)
                        ? "✅ Bokning " + bookingId + " KLAR. Slutpris: " + booking.getFinalPrice() + " kr."
                        : "✅ Bokning " + bookingId + " KLAR.";
                loggingService.logInfo(doneMsg);
                return doneMsg;

            } catch (IllegalArgumentException | IllegalStateException ex) {
                String err = "❗ Kunde inte avsluta bokning " + bookingId + ": " + ex.getMessage();
                loggingService.logError(err);
                return err;
            }
        }

        // --------------------------------------------------

        /**
         * Tar bort en bokning
         */
        public void removeBooking ( int bookingId){
            bookingRepository.findById(bookingId).ifPresentOrElse(
                    booking -> {
                        bookingRepository.remove(bookingId);
                        loggingService.logInfo("Bokning med ID: " + booking + " har tagits bort.");
                    },
                    () -> loggingService.logError("Bokning med ID " + bookingId + " hittades inte.")
            );
        }

        // --------------------------------------------------

        /**
         * Skriver ut alla bokningar till konsolen
         */
        public void printAllBookings () {
            List<Booking> bookings = bookingRepository.findAll();
            Optional.of(bookings).filter(list -> !list.isEmpty())
                    .ifPresentOrElse(list -> list.forEach(b -> System.out.println(b)),
                            () -> System.out.println("Inga bokningar är tillgängliga."));
        }

    }

