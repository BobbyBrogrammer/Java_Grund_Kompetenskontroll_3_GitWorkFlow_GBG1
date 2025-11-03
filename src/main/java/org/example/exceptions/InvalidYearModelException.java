package org.example.exceptions;

public class InvalidYearModelException extends RuntimeException {
    public InvalidYearModelException(String message) {
        super(message);
    }
    public InvalidYearModelException(String message, Throwable cause){
        super(message, cause);
    }
}
