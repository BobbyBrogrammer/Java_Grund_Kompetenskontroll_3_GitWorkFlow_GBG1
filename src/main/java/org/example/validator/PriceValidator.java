package org.example.validator;

import org.example.exceptions.InvalidPriceException;

public class PriceValidator {
    public void validatePrice(double price) {
        if (price <= 500)
            throw new InvalidPriceException("Priset för lagning kan inte vara under 500kr ");
    }

    // Validerar priser
    public void validateBasePrice(double price) {
        if (price < 0) {
            throw new InvalidPriceException("Grundpris kan inte vara negativt. Angivet pris: " + price);
        }
    }

    // Validerar pris för reparation (manuellt)
    public void validateRepairPrice(double price) {
        if (price <=0) {
            throw new InvalidPriceException("Priset för reparation måste vara större än 0 kr. Angivet: " + price);

        }
        if (price > 100_000) {
            throw new InvalidPriceException("Priset för reparation verkar orimligt högt: " + price + " kr.");
        }
    }

    }

