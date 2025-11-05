package org.example.validator;

import org.example.exceptions.InvalidPriceException;

public class PriceValidator {
    public void validatePrice(double price) {
        if (price <= 500)
            throw new InvalidPriceException("Priset för lagning kan inte vara under 500kr ");
    }
}