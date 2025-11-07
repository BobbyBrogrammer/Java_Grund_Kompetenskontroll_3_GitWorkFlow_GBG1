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
                    logger.logWarn("Användaren avslutade programmet.");
                    logger.logError("Test: avslutningslogg för kontroll.");
                    output.printExitProgram();
                    return;
                    //System.exit(0);
                }
                default ->{
                    logger.logInfo("Ogiltigt menyval: " + choice);
                    output.printDefaultMenuChoice();
                }
            }
        }
    }

}
