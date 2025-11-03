package org.example.models;

import java.time.LocalDate;

public class RepairBooking extends Booking {
    private String actionDescription; // Vad som ska repareras

<<<<<<< HEAD
    public RepairBooking(Vehicle vehicle, LocalDate date, double price, Customer customer, String actionDescription) {
        super(vehicle, date, price, customer, BookingType.REPAIR);
=======
    public RepairBooking(Vehicle vehicle, LocalDate date, double price, String customerEmail, String actionDescription) {
        super(vehicle, date, price, customerEmail, BookingType.REPAIR);
>>>>>>> 434a660 (Adnan Person 1 Domain)
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