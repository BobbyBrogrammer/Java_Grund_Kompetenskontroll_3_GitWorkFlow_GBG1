package org.example.service;

public class PriceCalculatorService {
    public double calculateService(String vehicleType) {
        switch (vehicleType.toLowerCase()) {
            case "car":
                return 500.0;
            case "truck":
                return 800.0;
            case "motorcycle":
                return 300.0;
            default:
                System.out.println("Okänd fordonstyp, pris sätts till 0 kr.");
                return 0.0;
        }
    }
}
