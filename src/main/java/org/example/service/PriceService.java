package org.example.service;

import org.example.models.BookingType;
import org.example.models.Vehicle;
import org.example.validator.PriceValidator;

public class PriceService {
   private static final double INSPECTION_PRICE = 550.0;


        private final PriceValidator priceValidator;

        public PriceService(PriceValidator priceValidator) {
            this.priceValidator = priceValidator;
    }
        public double bookInspection() {
        // validerar grundpris:
        priceValidator.validateBasePrice(INSPECTION_PRICE);
        return INSPECTION_PRICE;
    }


    public double calculatePrice(BookingType bookingType, Vehicle vehicle) {
        return switch (bookingType) {
            case INSPECTION -> getInspectionPrice();
            case SERVICE   -> calculateServicePrice(vehicle.getYearModel());
            case REPAIR    -> calculateRepairPrice(vehicle.getYearModel());
        };
    }
    public double calculateRepairPrice(int yearModel) {
        double price;

        if (yearModel > 2020) {
            price = 2500.0;
        } else if (yearModel >= 2015) {
            price = 3000.0;
        } else if (yearModel >= 2010) {
            price = 3500.0;
        } else if (yearModel >= 2005) {
            price = 4000.0;
        } else {
            price = 4500.0;
        }
        priceValidator.validateBasePrice(price);
        return price;
    }


        public double getInspectionPrice() {
        return INSPECTION_PRICE;
        }

        public double calculateServicePrice(int yearModel) {
            double price;

            if (yearModel > 2020) {
                return 1500.0;
            } else if (yearModel >= 2015) {
                return 1800.0;
            } else if (yearModel >= 2010) {
                return 2000.0;
            } else if (yearModel >= 2005) {
                price = 2300.0;
            } else {
                price = 2800.0;
            }
        priceValidator.validateBasePrice(price);
        return price;
        }
}
