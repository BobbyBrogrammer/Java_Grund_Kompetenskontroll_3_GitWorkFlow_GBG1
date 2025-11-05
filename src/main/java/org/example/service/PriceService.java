package org.example.service;

import org.example.models.BookingType;
import org.example.models.Vehicle;

import java.util.LinkedHashMap;
import java.util.Map;

public class PriceService {
   public static final double INSPECTION_PRICE = 550.0;

        public double calculatePrice(BookingType bookingType, Vehicle vehicle) {
        return switch (bookingType) {
            case INSPECTION -> getInspectionPrice();
            case SERVICE -> calculateServicePrice(vehicle.getYearModel());
            case REPAIR -> 0.0;
        };
    }

        public double getInspectionPrice() {
        return INSPECTION_PRICE;
        }

        private static final Map<Integer, Double> SERVICE_PRICES = Map.ofEntries(
                Map.entry(2021, 1500.0),
                Map.entry(2015, 1800.0),
                Map.entry(2010, 2000.0),
                Map.entry(2005, 2300.0),
                Map.entry(0, 2800.0)
        );


        public double calculateServicePrice(int yearModel) {
            return SERVICE_PRICES.entrySet().stream()
                    .sorted((a, b) -> b.getKey().compareTo(a.getKey()))//Sortera nycklar i fallande ordning
                    .filter(e -> yearModel >= e.getKey())
                    .findFirst()
                    .map(Map.Entry::getValue)
                    .orElse(2800.0);
        }


        public double confirmRepairPrice(double repairPrice) {
            if (repairPrice <= 0) {
                throw new IllegalArgumentException("Priset för reparation måste vara större än 0 kr.");
            }
            return repairPrice;
        }




}
