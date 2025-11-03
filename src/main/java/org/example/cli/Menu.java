package org.example.cli;

import org.example.systemIO.IIO;
import org.example.systemIO.SystemIO;

import static java.lang.Integer.parseInt;

public class Menu {
    private final IIO io;
    private final ConsoleUI ui;

    public Menu(SystemIO io, ConsoleUI ui) {
        this.io = io;
        this.ui = ui;
    }

        public void showMainMenu () {
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
                    case 1 -> ui.createBooking();
                    case 2 -> ui.showAllBookings();
                    case 3 -> ui.searchBooking();
                    case 4 -> ui.deleteBooking();
                    case 5 -> ui.updateBooking();
                    case 0 -> io.printLine("Avslutar programmet. Hej då!");
                    default -> io.printLine("Ogiltigt val, försök igen!");
                }

                io.printLine("");

            } while (choice != 0);
        }

        private int parseInt (String input){
            try {
                return parseInt(input);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

