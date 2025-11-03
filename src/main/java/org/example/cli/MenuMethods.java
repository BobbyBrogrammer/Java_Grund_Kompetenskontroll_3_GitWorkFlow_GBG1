package org.example.cli;

import org.example.service.CompletionService;
import org.example.systemIO.IIO;

import java.util.ArrayList;
import java.util.List;

public class MenuMethods {
    private final IIO io;
    private final CompletionService completionService;
    private final List<String> bookings = new ArrayList<>();

    public MenuMethods(IIO io) {
        this.io = io;
        this.completionService = new CompletionService();
    }

    public void createBooking() {
        io.printLine("\n=== Skapa ny bokning ===");
        io.printLine("Ange kundens e-postadress: ");
        String email = io.readLine();

        io.printLine("Ange registreringsnummer (t.ex. ABC123): ");
        String regNumber = io.readLine();

        io.printLine("Ange fordonstyp (car/truck/motorcycle): ");
        String vehicleType = io.readLine();

        completionService.completeProcess(email, regNumber, vehicleType);
        bookings.add(regNumber + " (" + vehicleType + ")");
        io.printLine("Bokning skapad och tillagd i systemet!");
    }

    public void showAllBookings() {
        io.printLine("\n=== Alla bokningar ===");
        if (bookings.isEmpty()) {
            io.printLine("Inga bokningar registrerade ännu.");
        } else {
            for (int i = 0; i < bookings.size(); i++) {
                io.printLine((i + 1) + ". " + bookings.get(i));
            }
        }
    }

    public void searchBooking() {
        io.printLine("\n=== Sök bokning ===");
        io.printLine("Ange registreringsnummer: ");
        String search = io.readLine();
        boolean found = false;

        for (String booking : bookings) {
            if (booking.contains(search.toUpperCase())) {
                io.printLine("Bokning hittad: " + booking);
                found = true;
            }
        }

        if (!found) {
            io.printLine("Ingen bokning hittades med det registreringsnumret.");
        }
    }

    public void deleteBooking() {
        io.printLine("\n=== Ta bort bokning ===");
        io.printLine("Ange registreringsnummer: ");
        String search = io.readLine();
        boolean removed = bookings.removeIf(b -> b.contains(search.toUpperCase()));

        if (removed) {
            io.printLine("Bokningen togs bort.");
        } else {
            io.printLine("Ingen bokning hittades att ta bort.");
        }
    }

    public void updateBooking() {
        io.printLine("\n=== Uppdatera bokning ===");
        io.printLine("Ange registreringsnummer: ");
        String search = io.readLine();

        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).contains(search.toUpperCase())) {
                io.printLine("Ange ny fordonstyp: ");
                String newVehicle = io.readLine();
                bookings.set(i, search.toUpperCase() + " (" + newVehicle + ")");
                io.printLine("Bokningen har uppdaterats.");
                return;
            }
        }

        io.printLine("Ingen bokning hittades att uppdatera.");
    }
}
