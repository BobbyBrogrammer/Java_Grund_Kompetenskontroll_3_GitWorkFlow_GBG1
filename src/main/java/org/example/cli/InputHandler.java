package org.example.cli;

import org.example.service.ValidationService;
import org.example.systemIO.IIO;

import java.time.LocalDate;

public class InputHandler {
    private final IIO io;
    private final ValidationService validationService;

    public InputHandler(IIO io, ValidationService validationService) {
        this.io = io;
        this.validationService = validationService;
    }

    public String readCustomerName() {
        while (true) {
            io.printLine("Ange kundens namn: ");
            String name = io.readLine().trim();
            if (!name.isBlank()) {
                return name;
            }
            io.printLine("Namn får inte vara tomt, försök igen!");
        }
    }
    public String readEmail() {
        while (true) {
            io.printLine("Ange kundens e-postadress: ");
            String email = io.readLine().trim();
            if (validationService.validateEmail(email)) {
                return email;
            }
            io.printLine("Ogiltig e-post, försök igen!");
        }
    }

    public String readPhoneNumber() {
        while (true) {
            io.printLine("Ange kundens telefonummer: ");
            String phone = io.readLine().trim();
            if (!phone.isBlank() && phone.matches("\\d{6,15}")) {
                return phone;
            }
            io.printLine("Ogiltigt telefonnummer (endast siffror, 6-15 tecken!");
        }
    }

    public String readRegistrationNumber() {
        while (true) {
            io.printLine("Ange registreringsnummer, exempel (ABC123)");
            String reg = io.readLine().trim().toUpperCase();
            if (validationService.validateRegistrationNumber(reg)) {
                return reg;
            }
            io.printLine("Ogiltigt registreringsnummer, försök igen!");
        }
    }

    public String readVehicleModel() {
        while (true) {
            io.printLine("Ange fordonets modell: exempel (Audi A4)");
            String model = io.readLine().trim();
            if (!model.isBlank()) {
                return model;
            }
            io.printLine("Modell får inte vara tom, försök igen!");
        }
    }

    public int readYearModel() {
        while (true) {
            io.printLine("Ange årsmodell: exempel (2005)");
            try {
                int year = Integer.parseInt(io.readLine().trim());
                if (year > 1950 && year <= LocalDate.now().getYear()) {
                    return year;
                }
                io.printLine("Ogiltig årsmodell, försök igen!");
            } catch (NumberFormatException ex) {
                io.printLine("Du måste skriva in siffror.");
            }
        }
    }

    public LocalDate readDate() {
        while (true) {
            io.printLine("Ange datum (ÅÅÅÅ-MM-DD): ");
            String date = io.readLine().trim();
            if (validationService.isValidDate(date)) {
                return LocalDate.parse(date);
            }
            io.printLine("Ogiltigt datumformat.");
        }
    }











//    public int getIntInput() {
//        while (true) {
//            try {
//                int input = scanner.nextInt();
//                scanner.nextLine(); // Rensa newline
//                return input;
//            } catch (InputMismatchException e) {
//                System.out.print("Ogiltig inmatning. Ange en siffra: ");
//                scanner.nextLine(); // Rensa felaktig input
//            }
//        }
//    }
//    public String getStringInput(String prompt) {
//        System.out.print(prompt);
//        return scanner.nextLine().trim();
//    }


}
