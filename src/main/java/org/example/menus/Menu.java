package org.example.menus;

import org.example.cli.BookingUI;
import org.example.cli.InputHandler;
import org.example.cli.OutputHandler;
import org.example.service.LoggingService;
import org.example.systemIO.IIO;

public class Menu {
    private final IIO io;
    private final InputHandler input;
    private final OutputHandler output;
    private final BookingUI bookingUI;
    private final BookingSelectionMenu bookingSelectionMenu;
    private final LoggingService logger;

    public Menu(IIO io, InputHandler input, OutputHandler output, BookingUI bookingUI, BookingSelectionMenu bookingSelectionMenu, LoggingService logger) {
        this.io = io;
        this.output = output;
        this.input = input;
        this.bookingUI = bookingUI;
        this.bookingSelectionMenu = bookingSelectionMenu;
        this.logger = logger;

    }

    public void showServiceMenu() {
        while (true) {
            output.printServiceChoices();
            String choice = io.readLine().trim();

            switch (choice) {
                case "1" -> {
                    logger.logInfo("Användaren valde besiktningsbokning. ");
                    output.printBesiktningChoice();
                    showInspectionMenu();
                }
                case "2" -> {
                    logger.logInfo("Användaren valde reparationsbokning. ");
                    output.printReparationChoice();
                    showRepairMenu();
                }
                case "3" -> {
                    logger.logInfo("Användaren valde servicebokning. ");
                    output.printServiceChoice();
                    showServiceBookingMenu();
                }
                case "0" -> {
                    logger.logInfo("Användaren avslutade service-menyn. ");
                    output.printExitProgram();
                    return;
                }
                default ->{
                    logger.logInfo("Felaktigt menyval: " + choice);
                    output.printDefaultMenuChoice();
                }
            }
        }
    }

    public void showMainMenu() {
        while (true) {
            output.printMenyChoices();
            String choice = io.readLine().trim();
            switch (choice) {
                case "1" -> {
                    logger.logInfo("Valde: Skapa bokning. ");
                    bookingSelectionMenu.runMenu();
                }
                case "2" -> {
                    logger.logInfo("Valde: Visa alla bokningar. ");
                    bookingUI.showAllBookings();
                }
                case "3" ->{
                    logger.logInfo("Valde: Sök bokning. ");
                    bookingUI.searchBooking();
                }
                case "4" -> {
                    logger.logInfo("Valde: Uppdatera bokning. ");
                    bookingUI.updateBooking();
                }
                case "5" -> {
                    logger.logInfo("Valde: Ta bort bokning. ");
                    bookingUI.deleteBooking();
                }
                case "6" ->{
                    logger.logInfo("Valde: Gå tillbaka till huvudmeny. ");
                    showMainMenu();
                }
                case "0" -> {
                    logger.logInfo("Program avslutas från huvudmeny. ");
                    output.printExitProgram();
                    System.exit(0);
                }
                default ->{
                    logger.logInfo("Ogiltigt menyval: " + choice);
                    output.printDefaultMenuChoice();
                }
            }
        }
    }

    public void showInspectionMenu() {
        while (true) {
            output.printMenyChoicesInspection();
            String choice = io.readLine().trim();

            switch (choice) {
                case "1" ->{
                    bookingUI.createBooking();

                }
                case "2" -> {
                    logger.logInfo("Valde: visa alla bokningar. ");
                    bookingUI.showAllBookings();
                }
                case "3" ->{
                    logger.logInfo("Valde: sök bokning. ");
                    bookingUI.searchBooking();
                }
                case "4" -> {
                    logger.logInfo("Valde: uppdatera bokning. ");
                    bookingUI.updateBooking();
                }
                case "5" -> {
                    logger.logInfo("Valde: ta bort bokning. ");
                    bookingUI.deleteBooking();
                }
                case "6" -> {
                    logger.logInfo("Programmet avs");
                    showServiceMenu();
                } // Gå tillbaka
                case "0" -> {
                    output.printExitProgram();
                    System.exit(0);
                }
                default -> output.printDefaultMenuChoice();
            }
        }
    }

    public void showRepairMenu() {
        while (true) {
            output.printMenyChoicesRepair();
            String choice = io.readLine().trim();

            switch (choice) {
                case "1" -> bookingUI.createBooking();
                case "2" -> bookingUI.showAllBookings();
                case "3" -> bookingUI.searchBooking();
                case "4" -> bookingUI.updateBooking();
                case "5" -> bookingUI.deleteBooking();
                case "6" -> showServiceMenu(); // Tillbaka
                case "0" -> {
                    output.printExitProgram();
                    System.exit(0);
                }
                default -> output.printDefaultMenuChoice();
            }
        }
    }

    public void showServiceBookingMenu() {
        while (true) {
            output.printMenyChoicesService();
            String choice = io.readLine().trim();

            switch (choice) {
                case "1" -> bookingUI.createBooking();
                case "2" -> bookingUI.showAllBookings();
                case "3" -> bookingUI.searchBooking();
                case "4" -> bookingUI.updateBooking();
                case "5" -> bookingUI.deleteBooking();
                case "6" -> showServiceMenu();
                case "0" -> {
                    output.printExitProgram();
                    System.exit(0);
                }
                default -> output.printDefaultMenuChoice();
            }
        }
    }
}
