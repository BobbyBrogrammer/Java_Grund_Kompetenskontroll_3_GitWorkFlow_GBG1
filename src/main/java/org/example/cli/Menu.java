package org.example.cli;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final InputHandler inputHandler;
    private final MenuMethods menuMethods;

    public Menu() {
        scanner = new Scanner(System.in);
        inputHandler = new InputHandler(scanner);
        menuMethods = new MenuMethods(inputHandler);
    }

    public void showMainMenu() {
        int choice;
        do {
            System.out.println("=== Bilmeckarna AB ===");
            System.out.println("1. Skapa ny bokning");
            System.out.println("2. Visa alla bokningar");
            System.out.println("3. Sök bokning");
            System.out.println("4. Ta bort bokning");
            System.out.println("5. Uppdatera bokning");
            System.out.println("0. Avsluta");
            System.out.print("Välj ett alternativ: ");

            choice = inputHandler.getIntInput();

            switch (choice) {
                case 1 -> menuMethods.createBooking();
                case 2 -> menuMethods.showAllBookings();
                case 3 -> menuMethods.searchBooking();
                case 4 -> menuMethods.deleteBooking();
                case 5 -> menuMethods.updateBooking();
                case 0 -> System.out.println("Avslutar programmet. Hej då!");
                default -> System.out.println("Ogiltigt val, försök igen!");
            }

            System.out.println();

        } while (choice != 0);
    }
}
