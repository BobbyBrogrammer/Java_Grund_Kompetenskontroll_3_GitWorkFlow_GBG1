package org.example.service;

import org.example.models.BookingType;
import org.example.models.Vehicle;
import org.example.validator.PriceValidator;

/**
 * PriceService ansvarar för att beräkna priser för olika typer av bokningar:
 * - INSPECTION (besiktning)
 * - SERVICE
 * - REPAIR (reparation)
 */
public class PriceService {
    // Grundpris för besiktning (konstant)
    private static final double INSPECTION_PRICE = 550.0;

    private final PriceValidator priceValidator; // Validerar priser

    public PriceService(PriceValidator priceValidator) {
        this.priceValidator = priceValidator;
    }

    /**
     * Returnerar priset för en besiktning.
     * Validerar grundpriset innan det returneras.
     */
    public double bookInspection() {
        priceValidator.validateBasePrice(INSPECTION_PRICE);
        return INSPECTION_PRICE;
    }

    /**
     * Beräknar pris för alla bokningstyper.
     * @param bookingType Typ av bokning (INSPECTION, SERVICE, REPAIR)
     * @param vehicle Fordonet som bokas
     * @return Pris för bokningen
     */
    public double calculatePrice(BookingType bookingType, Vehicle vehicle) {
        return switch (bookingType) {
            case INSPECTION -> getInspectionPrice();
            case SERVICE -> calculateServicePrice(vehicle.getYearModel());
            case REPAIR -> calculateRepairPrice(vehicle.getYearModel());
        };
    }

    /**
     * Beräknar pris för reparation baserat på bilens årsmodell.
     * Nyare bilar har lägre pris, äldre högre.
     * Validerar priset innan return.
     */
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

    /**
     * Returnerar grundpris för besiktning.
     */
    public double getInspectionPrice() {
        return INSPECTION_PRICE;
    }

    /**
     * Beräknar pris för service baserat på bilens årsmodell.
     * Nyare bilar har lägre pris, äldre högre.
     * Validerar priset innan return.
     */
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
