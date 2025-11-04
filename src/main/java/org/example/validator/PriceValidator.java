package org.example.validator;

import org.example.exceptions.InvalidPriceException;

public class PriceValidator implements Validator{
    public boolean validatePrice(double price) {
        if (price <= 500) {
            throw new InvalidPriceException("Priset för lagning kan inte vara under 500kr ");
        }
        return true;
    }

    @Override
    public boolean isValid(Object price){
        return true;
    }
}
