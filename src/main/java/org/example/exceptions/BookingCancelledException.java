package org.example.exceptions;

public class BookingCancelledException extends RuntimeException {
    public BookingCancelledException() {
        super("Bokning avbruten av användaren");
    }
}
