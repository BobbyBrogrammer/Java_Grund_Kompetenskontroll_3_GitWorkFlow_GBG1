package org.example.service;

import java.time.LocalDateTime;

public class LoggingService {
    public void log(String message) {
        System.out.println("[" + LocalDateTime.now() + "] LOG: " + message);
    }

    public void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public void logError(String message) {
        System.err.println("[ERROR] " + message);
    }
}
