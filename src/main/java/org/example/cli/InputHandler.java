package org.example.cli;

import org.example.models.Status;
import org.example.service.ValidationService;
import org.example.systemIO.IIO;

import java.time.LocalDate;

public class InputHandler {
    private final OutputHandler output;
    private final IIO io;
    private final ValidationService validationService;

    public InputHandler(OutputHandler output, IIO io, ValidationService validationService) {
        this.output = output;
        this.io = io;
        this.validationService = validationService;
    }

    public String readCustomerName() {
        while (true) {
            output.printStateCustomerName();
            String name = io.readLine().trim();
            if (!name.isBlank()) {
                return name;
            }
            output.printEmptyNameNotAllowed();
        }
    }
    public String readEmail() {
        while (true) {
            output.printStateCustomerEmail();
            String email = io.readLine().trim();
            if (validationService.validateEmail(email)) {
                return email;
            }
            output.printWrongEmail();
        }
    }

    public String readPhoneNumber() {
        while (true) {
            output.printStateCustomerPhoneNumber();
            String phone = io.readLine().trim();
            if (!phone.isBlank() && phone.matches("\\d{6,15}")) {
                return phone;
            }
            output.printWrongPhoneNumber();
        }
    }

    public String readRegistrationNumber() {
        while (true) {
            output.printStateCarRegNumber();
            String reg = io.readLine().trim().toUpperCase();
            if (validationService.validateRegistrationNumber(reg)) {
                return reg;
            }
            output.printWrongRegNumber();
        }
    }

    public String readVehicleModel() {
        while (true) {
            output.printStateCarModel();
            String model = io.readLine().trim();
            if (!model.isBlank()) {
                return model;
            }
            output.printModelCantBeEmpty();
        }
    }

    public int readYearModel() {
        while (true) {
            output.printStateYearModel();
            try {
                int year = Integer.parseInt(io.readLine().trim());
                if (year > 1950 && year <= LocalDate.now().getYear()) {
                    return year;
                }
                output.printWrongYearModel();
            } catch (NumberFormatException ex) {
                output.printWrongNumberInput();
            }
        }
    }

    public LocalDate readDate() {
        while (true) {
            output.printInputDate();
            String date = io.readLine().trim();
            if (validationService.isValidDate(date)) {
                return LocalDate.parse(date);
            }
            output.printWrongDate();
        }

    }

    public boolean readYesNo() {
        while (true) {
            String answer = io.readLine().trim().toUpperCase();

            if (answer.equals("Y") || answer.equals("J")) {   // Y eller J = ja
                return true;
            }
            if (answer.equals("N")) {
                return false;
            }

            output.printError("Skriv Y/J för ja eller N för nej.");
        }
    }

    public int readBookingId() {
        while (true) {
            output.askForBookingId();
            try {
                int id = Integer.parseInt(io.readLine().trim());
                if (id > 0) return id;
                output.printIdMustBeGreaterThanZero();
            } catch (NumberFormatException ex) {
                output.printIdMustBeNumbers();
            }
        }
    }
    public String readUpdateChoice() {
        output.printUpdateBookingOptions();
        return io.readLine().trim();
    }

    public Status readBookingStatus() {
        while (true) {
            output.printStateNewStatus();
            String statusInput = io.readLine().trim().toUpperCase();
            try {
                return Status.valueOf(statusInput);
            } catch (IllegalArgumentException ex) {
                output.printStatusNotCorrect();
            }
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
