package org.example.models;

import java.time.LocalDate;

public class RepairBooking extends Booking {
    private String actionDescription; // Vad som ska repareras

    public RepairBooking(Vehicle vehicle, LocalDate date, double price, Customer customer, String actionDescription) {
        super(vehicle, date, price, customer, BookingType.REPAIR);
        this.actionDescription = actionDescription;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    @Override
    public String toString() {
        return super.toString() + " | Action: " + actionDescription;
    }
}