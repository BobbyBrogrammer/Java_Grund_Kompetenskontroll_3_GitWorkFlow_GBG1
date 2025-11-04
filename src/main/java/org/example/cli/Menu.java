package org.example.cli;

import org.example.service.CompletionService;
import org.example.systemIO.IIO;

public class Menu {
    private final IIO io;
    private final OutputHandler output;
    private final ConsoleUI consoleUI;

    public Menu(IIO io, InputHandler input, OutputHandler output, CompletionService completionService) {
        this.io = io;
        this.output = output;
        this.consoleUI = new ConsoleUI(io, input, output, completionService);
    }

    public void showServiceMenu() {
        while (true) {
            output.printServiceChoices();
            String choice = io.readLine().trim();

            switch (choice) {
                case "1" -> {
                    output.printBesiktningChoice();
                    showMainMenu();
                }
                case "2" -> {
                    output.printReparationChoice();
                    showMainMenu();
                }
                case "3" -> {
                    output.printServiceChoice();
                    showMainMenu();
                }
                case "0" -> {
                    output.printExitProgram();
                    return;
                }
                default -> output.printDefaultMenuChoice();
            }
        }
    }

    public void showMainMenu() {
        while (true) {
            output.printMenyChoices();
            String choice = io.readLine().trim();

            switch (choice) {
                case "1" -> consoleUI.createBooking();
                case "2" -> consoleUI.showAllBookings();
                case "3" -> consoleUI.searchBooking();
                case "4" -> consoleUI.deleteBooking();
                case "5" -> consoleUI.updateBooking();
                case "6" -> showServiceMenu(); 
                case "0" -> output.printExitProgram();
                default -> output.printDefaultMenuChoice();
            }
        }
    }
}

