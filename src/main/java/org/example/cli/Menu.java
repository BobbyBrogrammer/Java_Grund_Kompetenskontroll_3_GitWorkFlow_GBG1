package org.example.cli;

import org.example.systemIO.IIO;
import org.example.systemIO.SystemIO;

import java.util.Scanner;

public class Menu {
    private final IIO io;
    private final MenuMethods menuMethods;

    public Menu() {
        this.io = new SystemIO();
        this.menuMethods = new MenuMethods(io);
    }

    public void showMainMenu() {
        int choice;
        do {
            io.printLine("=== Bilmeckarna AB ===");
            io.printLine("1. Skapa ny bokning");
            io.printLine("2. Visa alla bokningar");
            io.printLine("3. Sök bokning");
            io.printLine("4. Ta bort bokning");
            io.printLine("5. Uppdatera bokning");
            io.printLine("0. Avsluta");
            io.printLine("Välj ett alternativ: ");

            // Läs in val
            String input = io.readLine();
            choice = parseInt(input);

            switch (choice) {
                case 1 -> menuMethods.createBooking();
                case 2 -> menuMethods.showAllBookings();
                case 3 -> menuMethods.searchBooking();
                case 4 -> menuMethods.deleteBooking();
                case 5 -> menuMethods.updateBooking();
                case 0 -> io.printLine("Avslutar programmet. Hej då!");
                default -> io.printLine("Ogiltigt val, försök igen!");
            }

            io.printLine("");

        } while (choice != 0);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
