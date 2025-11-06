package org.example.service;




import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);


    public void logInfo(String message) {
        logger.info(message);
    }

    public void logError(String message) {
        logger.error(message);
    }

    public void logDebug(String message) {
        logger.debug(message);
    }
    public void logWarn(String message){logger.warn(message);}
}
