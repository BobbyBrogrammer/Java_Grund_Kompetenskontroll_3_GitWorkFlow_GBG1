package org.example.validator;

import org.example.exceptions.InvalidRegistrationNumberException;
import org.example.exceptions.InvalidYearModelException;
import org.example.models.Vehicle;

public class VehicleValidator implements Validator<Object>{


    public boolean validateRegistrationNumber(String reg) {
        if (!reg.matches("^[A-ZÅÄÖ]{3}[0-9]{2}[A-Z0-9ÅÄÖ]$")) {
            throw new InvalidRegistrationNumberException("Ogiltigt registreringsnummer " + reg);
        } else return true;
    }

    public boolean validateModel(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Bil modell måste anges får inte vara null ");
        } else return true;
    }

    public boolean validateYearModel(int year) {
        if (year < 1900 || year >= 2026) {
            throw new InvalidYearModelException("Årsmodell kan inte vara under år 1900 eller över 2026 " + year);
        }
        return true;
    }

    @Override
    public boolean isValid(Object vehicle){
        return true;
    }
}
