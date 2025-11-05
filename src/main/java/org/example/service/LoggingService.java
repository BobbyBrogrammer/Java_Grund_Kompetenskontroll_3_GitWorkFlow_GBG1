package org.example.service;




import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);


    public void info(String message) {
        System.out.println("[" + LocalDateTime.now() + "] LOG: " + message);
    }

    public void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public void logError(String message) {
        System.err.println("[ERROR] " + message);
    }
}
