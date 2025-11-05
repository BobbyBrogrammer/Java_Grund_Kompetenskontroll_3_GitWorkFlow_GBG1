package org.example.menus;

import org.example.cli.ConsoleUI;
import org.example.cli.OutputHandler;
import org.example.systemIO.IIO;

public class BookingSelectionMenu {
   private final IIO io;
   private final OutputHandler output;
   private final ConsoleUI ui;


    public BookingSelectionMenu(IIO io, OutputHandler output, ConsoleUI ui) {
        this.io = io;
        this.output = output;
        this.ui = ui;
    }

    public void runMenu(){
        boolean running = true;
         while (running){
             output.printChooseBookingMenu();
             String choice = io.readLine().trim();
             switch (choice){
                 case "1" ->{ui.createInspectionBooking();
                 running = false; }
                 case "2" ->{ui.createServiceBooking();
                 running = false;}
                 case "3" ->{ui.createRepairBooking();
                 running = false; }
                 case "0" ->{running = false;}
                 default -> {io.printLine("Ogiltigt val välj mellan 0-3 ");}
             }
         }
    }
}