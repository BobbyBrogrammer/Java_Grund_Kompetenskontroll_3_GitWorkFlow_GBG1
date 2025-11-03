package org.example.service;

import java.time.LocalDateTime;

public class LoggingService {
    public void log(String message) {
        System.out.println("[" + LocalDateTime.now() + "] LOG: " + message);
    }
}
