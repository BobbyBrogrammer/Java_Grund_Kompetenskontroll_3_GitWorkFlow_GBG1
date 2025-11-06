package org.example.service;

import org.example.cli.OutputHandler;

public class MailService {

    private final OutputHandler output;

    public MailService(OutputHandler output) {
        this.output = output;
    }

    public void sendMail(String email, String message) {
        // Simulerar mejlutskick
            output.printSendMail(email, message);
    }
}
