package org.example.menus;

import org.example.cli.ConsoleUI;
import org.example.cli.OutputHandler;
import org.example.service.LoggingService;
import org.example.systemIO.IIO;

public class BookingSelectionMenu {
   private final IIO io;
   private final OutputHandler output;
   private final ConsoleUI ui;
   private final LoggingService logger;


    public BookingSelectionMenu(IIO io, OutputHandler output, ConsoleUI ui, LoggingService logger) {
        this.io = io;
        this.output = output;
        this.ui = ui;
        this.logger = logger;
    }

    public void runMenu(){
        boolean running = true;
         while (running){
             output.printChooseBookingMenu();
             String choice = io.readLine().trim();
             switch (choice){
                 case "1" ->{ui.createInspectionBooking();
                 running = false;
                     logger.logInfo("Användaren skapade en besiktningsbokning");
                     running = false;}
                 case "2" ->{ui.createServiceBooking();
                     logger.logInfo("Användaren skapade en servicebokning");
                 running = false;}
                 case "3" ->{ui.createRepairBooking();
                     logger.logInfo("Användaren skapade en reparationsbokning");
                 running = false; }
                 case "0" ->{
                     logger.logInfo("Användaren avslutade bokningsmenyn");
                     running = false;}

                 default -> {io.printLine("Ogiltigt val välj mellan 0-3 ");}
             }
         }
    }
}