package org.example.validator;

import org.example.exceptions.InvalidPriceException;

public class PriceValidator {


    // Validerar priser
    public void validateBasePrice(double price) {
        if (price < 0) {
            throw new InvalidPriceException("Grundpris kan inte vara negativt. Angivet pris: " + price);
        }
    }
}

