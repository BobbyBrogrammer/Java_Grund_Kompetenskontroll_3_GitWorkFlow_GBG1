package org.example.cli;

import org.example.service.CompletionService;
import org.example.systemIO.IIO;

public class Menu {
    private final IIO io;
    private final OutputHandler output;
    private final MenuMethods menuMethods;

    public Menu(IIO io, InputHandler input, OutputHandler output, CompletionService completionService) {
        this.io = io;
        this.output = output;
        this.menuMethods = new MenuMethods(io, input, output, completionService);
    }

    public void showMainMenu() {
        while (true) {
            output.printMenyChoices();
            String choice = io.readLine().trim();

            switch (choice) {
                case "1" -> menuMethods.createBooking();
                case "2" -> menuMethods.showAllBookings();
                case "3" -> menuMethods.searchBooking();
                case "4" -> menuMethods.deleteBooking();
                case "5" -> menuMethods.updateBooking();
                case "0" -> output.printExitProgram();
                default -> output.printDefaultMenuChoice();
            }
        }
    }
}

