package org.example.exceptions;

public class InvalidEmailAdressException extends RuntimeException {
    public InvalidEmailAdressException(String message) {
        super(message);
    }
    public InvalidEmailAdressException(String message, Throwable cause){
        super(message, cause);
    }
}
