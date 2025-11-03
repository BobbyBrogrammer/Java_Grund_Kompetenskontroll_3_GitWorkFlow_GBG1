package org.example.cli;

import org.example.service.CompletionService;

public class MenuMethods {
    private final InputHandler inputHandler;
    private final CompletionService completionService;

    public MenuMethods(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.completionService = new CompletionService();
    }

    public void createBooking() {
        System.out.println("\n=== Skapa ny bokning ===");
        String email = inputHandler.getStringInput("Ange kundens e-postadress: ");
        String regNumber = inputHandler.getStringInput("Ange registreringsnummer (t.ex. ABC123): ");
        String vehicleType = inputHandler.getStringInput("Ange fordonstyp (car/truck/motorcycle): ");

        completionService.completeProcess(email, regNumber, vehicleType);
    }

    public void showAllBookings() {
        System.out.println("Visar alla bokningar (funktionalitet kommer senare).");
    }

    public void searchBooking() {
        System.out.println("Söker bokning (funktionalitet kommer senare).");
    }

    public void deleteBooking() {
        System.out.println("Tar bort bokning (funktionalitet kommer senare).");
    }

    public void updateBooking() {
        System.out.println("Uppdaterar bokning (funktionalitet kommer senare).");
    }
}
