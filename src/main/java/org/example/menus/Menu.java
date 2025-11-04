package org.example.menus;

import org.example.cli.BookingUI;
import org.example.cli.InputHandler;
import org.example.cli.OutputHandler;
import org.example.systemIO.IIO;

public class Menu {
    private final IIO io;
    private final InputHandler input;
    private final OutputHandler output;
    private final BookingUI bookingUI;

    public Menu(IIO io, InputHandler input, OutputHandler output, BookingUI bookingUI) {
        this.io = io;
        this.output = output;
        this.input = input;
        this.bookingUI = bookingUI;

    }

//    public void showServiceMenu() {
//        while (true) {
//            output.printServiceChoices();
//            String choice = io.readLine().trim();
//
//            switch (choice) {
//                case "1" -> {
//                    output.printBesiktningChoice();
//                    showMainMenu();
//                }
//                case "2" -> {
//                    output.printReparationChoice();
//                    showMainMenu();
//                }
//                case "3" -> {
//                    output.printServiceChoice();
//                    showMainMenu();
//                }
//                case "0" -> {
//                    output.printExitProgram();
//                    return;
//                }
//                default -> output.printDefaultMenuChoice();
//            }
//        }
    }

   //public void showMainMenu() {
      //  while (true) {
      //      output.printMenyChoices();
          //  String choice = io.readLine().trim();

        //      case "1" -> bookingUI.createBooking();
//                case "2" -> bookingUI.showAllBookings();
//                case "3" -> bookingUI.searchBooking();
//                case "4" -> bookingUI.updateBooking();
//                case "5" -> bookingUI.deleteBooking();
//                case "6" -> showServiceMenu();
//                case "0" -> {
//                    output.printExitProgram();
//                    System.exit(0);
//                }
//                default -> output.printDefaultMenuChoice();
//            }
//        }
//    }
//}

