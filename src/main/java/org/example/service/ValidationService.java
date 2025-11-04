package org.example.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationService {
    // Enkel e-postvalidering
    public boolean validateEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        boolean valid = Pattern.matches(regex, email);
        if (!valid) {
            System.out.println("Ogiltig e-postadress: " + email);
        }
        return valid;
    }

    // Enkel validering av registreringsnummer (t.ex. ABC123)
    public boolean validateRegistrationNumber(String regNumber) {
        String regex = "^[A-ZÅÄÖ]{3}\\d{3}$"; // Svenska formatet, t.ex. ABC123
        boolean valid = Pattern.matches(regex, regNumber.toUpperCase());
        if (!valid) {
            System.out.println("Ogiltigt registreringsnummer: " + regNumber);
        }
        return valid;
    }

    public boolean isValidDate(String date) {
        if (date == null || date.isBlank()) {
            return false;
        }
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return !parsedDate.isBefore(LocalDate.now());
        } catch (DateTimeException ex) {
            System.out.println("Ogiltigt datumformat. Använd ÅÅÅÅ-MM-DD.");
            return false;
        }
    }
    public boolean isValidDate(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }

}
