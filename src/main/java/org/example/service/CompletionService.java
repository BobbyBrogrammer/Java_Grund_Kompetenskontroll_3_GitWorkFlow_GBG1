package org.example.service;

import org.example.models.Booking;
import org.example.models.BookingType;
import org.example.models.Vehicle;

public class CompletionService {
    private final PriceService priceService;
    private final ValidationService validationService;
    private final MailService mailService;
    private final LoggingService loggingService;

    public CompletionService(PriceService priceService, ValidationService validationService, MailService mailService, LoggingService loggingService) {
        this.priceService = priceService;
        this.validationService = validationService;
        this.mailService = mailService;
        this.loggingService = loggingService;
    }

    public void completeProcess(String email, String regNumber, BookingType bookingType, Vehicle vehicle) {
        loggingService.logInfo("Startar process för " + regNumber);

        // 1 Validera e-post
        boolean emailValid = validationService.validateEmail(email);

        // 2 Validera registreringsnummer
        boolean regValid = validationService.validateRegistrationNumber(regNumber);

        if (!emailValid || !regValid) {
            loggingService.logWarn("Validering misslyckades för " + regNumber);
            return;
        }

        // 3 Beräkna pris
        double price = priceService.calculatePrice(bookingType, vehicle);
        loggingService.logInfo("Pris beräknat: " + price + " kr för " + bookingType);

        // 4 Skicka mejl
        mailService.sendMail(email, regNumber, price, bookingType);
        // 5 Logga avslut
        loggingService.logInfo("Processen slutförd för kund " + email);
    }
        //När fordonet är klar
        public void notifyVehicleReady(Booking booking) {
            String email = booking.getCustomer().getEmail();
            String regNumber = booking.getVehicle().getRegistrationNumber();

            boolean emailValid = validationService.validateEmail(email);
            boolean regValid = validationService.validateRegistrationNumber(regNumber);

            if (!emailValid || !regValid) {
                loggingService.logWarn("Kunde inte skicka klart-mail, ogiltig data för bookingId=" + booking.getId());
                return;
            }

            double price = booking.isFlexiblePrice()
                    ? booking.getFinalPrice()
                    : booking.getPrice();

            String message = "Hej " + booking.getCustomer().getName() + "!\n\n" +
                    "Ditt fordon med registreringsnummer " + regNumber + " är nu klart för hämtning.\n" +
                    "Totalt pris: " + price + " kr för " + booking.getBookingType() + ".\n\n" +
                    "Vänliga hälsningar,\nBilmeckarna AB";

            // skickas mailet
            mailService.sendMail(email, regNumber, price, booking.getBookingType());

        }
}