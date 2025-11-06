package org.example.service;

import org.example.cli.OutputHandler;
import org.example.models.BookingType;

public class MailService {
    private final OutputHandler output;

    public MailService(OutputHandler output) {
        this.output = output;
    }

    public void sendMail(String email, String regNumber, double price, BookingType bookingType) {
        String message = """
                Hej!

                Ditt fordon med registreringsnummer %s är nu klart för hämtning.
                Totalt pris: %.2f kr för %s.

                Vänliga hälsningar,
                Bilmeckarna AB
                """.formatted(regNumber, price, bookingType);

        // Rubriken + själva meddelandet
        output.printSendMail(email, message);
    }
}
