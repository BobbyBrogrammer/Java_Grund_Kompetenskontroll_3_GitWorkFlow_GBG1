package org.example.service;

import org.example.models.BookingType;
import org.example.models.Vehicle;

public class PriceService {
   public double bookInspection(){
       return 550.0;
   }

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

        public double calculateServicePrice(int yearModel) {
            if (yearModel > 2020) {
                return 1500.0;
            } else if (yearModel >= 2015) {
                return 1800.0;
            } else if (yearModel >= 2010) {
                return 2000.0;
            } else if (yearModel >= 2005) {
                return 2300.0;
            } else {
                return 2800.0;
            }
        }

        public double confirmRepairPrice(double repairPrice) {
            if (repairPrice <= 0) {
                throw new IllegalArgumentException("Priset för reparation måste vara större än 0 kr.");
            }
            return repairPrice;
        }




}
