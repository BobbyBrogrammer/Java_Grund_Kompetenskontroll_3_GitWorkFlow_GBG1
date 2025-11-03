package org.example.service;

import org.example.models.BookingType;
import org.example.models.Vehicle;

public class CompletionService {
    private final PriceService priceService;
    private final ValidationService validationService;
    private final MailService mailService;
    private final LoggingService loggingService;

    public CompletionService() {
        this.priceService = new PriceService();
        this.validationService = new ValidationService();
        this.mailService = new MailService();
        this.loggingService = new LoggingService();
    }

    public void completeProcess(String email, String regNumber, BookingType bookingType, Vehicle vehicle) {
        loggingService.log("Startar process för " + regNumber);

        // 1 Validera e-post
        boolean emailValid = validationService.validateEmail(email);

        // 2 Validera registreringsnummer
        boolean regValid = validationService.validateRegistrationNumber(regNumber);

        if (!emailValid || !regValid) {
            loggingService.log("Validering misslyckades för " + regNumber);
            return;
        }

        // 3 Beräkna pris
        double price = priceService.calculatePrice(bookingType, vehicle);
        loggingService.log("Pris beräknat: " + price + " kr för " + bookingType);

        // 4 Skicka mejl
        mailService.sendMail(email, "Ditt pris är " + price + " kr för " + bookingType);

        // 5 Logga avslut
        loggingService.log("Processen slutförd för kund " + email);
    }
}
