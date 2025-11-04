package org.example.exceptions;


public class InvalidRegistrationNumberException extends RuntimeException {
    public InvalidRegistrationNumberException(String message) {
        super(message);
    }


    public InvalidRegistrationNumberException(String message, Throwable cause){
        super(message, cause);
    }
}
