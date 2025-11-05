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
             int choice = Integer.parseInt(io.readLine());
             switch (choice){
                 case 1 ->{ui.createInspectionBooking();}
                 case 2 ->{}
                 case 3 ->{}
                 case 0 ->{}
                 default -> {io.printLine("Ogiltigt val välj mellan 0-3 ");}
             }











         }
    }
}