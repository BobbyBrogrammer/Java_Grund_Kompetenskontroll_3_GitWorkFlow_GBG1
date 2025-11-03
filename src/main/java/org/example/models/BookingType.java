package org.example.models;

public enum BookingType {
    INSPECTION,  // Besiktning
    SERVICE,
    REPAIR;

    @Override
    public String toString() {
        switch (this) {
            case INSPECTION: return "Besiktning";
            case SERVICE: return "Service";
            case REPAIR: return "Reparation";
            default: return super.toString();
        }
    }
}