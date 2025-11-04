package org.example.cli;

import org.example.systemIO.IIO;

public class OutputHandler {
    private final IIO io;
    public OutputHandler(IIO io) {
        this.io = io;
    }
    //------------------------Skapa bokning--------------------
    public void printStateCreateNewBookingTitle() {
        io.printLine("\n=== Skapa ny bokning ===");
    }
    public void printStateCustomerName() {
        io.printLine("Ange kundens namn: ");
    }
    public void printStateCustomerEmail() {
        io.printLine("Ange kundens e-postadress: ");
    }
    public void printStateCustomerPhoneNumber() {
        io.printLine("Ange kundens telefonnummer: ");
    }
    public void printStateCarRegNumber() {
        io.printLine("Ange registreringsnummer, t.ex (ABC123)");
    }
    public void printStateCarModel() {
        io.printLine("Ange bilmärke och modell, t.ex (Volvo S60)");
    }
    public void printStateYearModel() {
        io.printLine("Ange årsmodell, t.ex (2009)");
    }
    public void printBookingSuccess() {
        io.printLine("Korrekt bokad och inläst i systemet!");
    }

    public void printError(String message) {
        io.printLine("Fel: " + message);
    }
    public void printSuccess(String message) {
        io.printLine("Kanon! " + message);
    }
    //------------------------------------------------------------------
    //-------------Alla bokningar-------------------------------
    public void printShowAllBookingsTitle() {
        io.printLine("\n=== Alla bokningar ===");
    }
    public void printIfNoBookings() {
        io.printLine("Inga bokningar registrerade ännu.");
    }
    //--------------------------------------------------------------
    //----------------Sök efter bokning---------------------------
    public void printSearchForBookingTitle() {
        io.printLine("\n=== Sök bokning ===");
    }
    public void printBookingFound() {
        io.printLine("Bokning hittad: ");
    }
    public void printNoBookingFound() {
        io.printLine("Ingen bokning hittades med det registreringsnumret");
    }
    //-----------------------------------------------------------------------
    //-------------------Ta bort bokning------------------------------------
    public void printRemoveBooking() {
        io.printLine("\n=== Ta bort bokning ===");
    }
    public void printBookingWasRemoved() {
        io.printLine("Bokningen togs bort.");
    }
    public void printNoBookingFoundToRemove() {
        io.printLine("Ingen bokning hittades att ta bort.");
    }
    //------------------------------------------------------------------
    //------------------Redigera bokning------------------------------
    public void printUpdateBookTitle() {
        io.printLine("\n=== Redigera bokning ===");
    }
    public void printStateNewVehicleInfoToUpdate() {
        io.printLine("Ange nytt fordon: ");
    }
    public void printBookingHaveBeenUpdated() {
        io.printLine("Bokningen har uppdaterats.");
    }
    public void printNoBookingFoundToUpdate() {
        io.printLine("Ingen bokning hittades att uppdatera.");
    }
    //-----------------------------------------------------------------
    //---------------------------MenyText------------------------------
    public void printMenyChoices() {
        io.printLine("\n--- Bilmeckarna ---");
        io.printLine("1. Skapa bokning");
        io.printLine("2. Visa alla bokningar");
        io.printLine("3. Sök bokning");
        io.printLine("4. Uppdatera bokning");
        io.printLine("5. Ta bort bokning");
        io.printLine("0. Avsluta");
    }
    public void printExitProgram() {
        io.printLine("Avslutar programmet...Hejdå!");
    }
    public void printDefaultMenuChoice() {
        io.printLine("Ogiltigt val, försök igen!");
    }
    //---------------------------InputHandler------------------------------
    public void printEmptyNameNotAllowed() {io.printLine("Namn får inte vara tomt, försök igen!");}
    public void printWrongEmail() {io.printLine("Ogiltig e-post, försök igen!");}
    public void printWrongPhoneNumber() {io.printLine("Ogiltigt telefonnummer (endast siffror, 6-15 tecken!");}
    public void printWrongRegNumber() {io.printLine("Ogiltigt registreringsnummer, försök igen!");}
    public void printModelCantBeEmpty() {io.printLine("Modell får inte vara tom, försök igen!");}
    public void printWrongYearModel() {io.printLine("Ogiltig årsmodell, försök igen!");}
    public void printWrongNumberInput() {io.printLine("Du måste skriva in siffror.");}
    public void printWrongDate() {io.printLine("Ogiltigt datumformat.");}
    public void printInputDate() {io.printLine("Ange datum (ÅÅÅÅ-MM-DD): ");}
}
