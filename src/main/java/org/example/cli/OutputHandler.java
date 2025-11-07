package org.example.cli;

import org.example.models.Booking;
import org.example.systemIO.IIO;

public class OutputHandler {
    private final IIO io;

    public OutputHandler(IIO io) {
        this.io = io;
    }

    //------------------------Skapa bokning--------------------
    public void printStateCreateNewBookingTitle() {
        io.printLine("\n\u001B[32m==== Skapa ny bokning ====\u001B[0m");
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
        io.printLine("\n\u001B[34m====  Alla bokningar  ====\u001B[0m");
    }

    public void printIfNoBookings() {
        io.printLine("Inga bokningar registrerade ännu.");
    }

    //--------------------------------------------------------------
    //----------------Sök efter bokning---------------------------
    public void printSearchForBookingTitle() {
        io.printLine("\n\u001B[34m====    Sök bokning   ====\u001B[0m");
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
        io.printLine("\n\u001B[31m==== Ta bort bokning  ====\u001B[0m");
    }

    public void printBookingWasRemoved() {
        io.printLine("Bokningen togs bort.");
    }

    public void printNoBookingFoundToRemove() {
        io.printLine("Ingen bokning hittades att ta bort.");
    }

    public void printConfirmDeleteQuestion() {
        io.printLine("Är du säker på att du vill ta bort denna bokning? (Y/N): ");
    }

    public void printDeleteCancelled() {
        io.printLine("Borttagning avbokad, bokningen ligger kvar.");
    }

    //------------------------------------------------------------------
    //------------------Redigera bokning------------------------------
    public void printUpdateBookTitle() {
        io.printLine("\n\u001B[33m==== Redigera bokning ====\u001B[0m");
    }

    public void printStateNewVehicleInfoToUpdate() {
        io.printLine("Ange nytt fordon: ");
    }

    public void printBookingHaveBeenUpdated(Booking booking) {
        io.printLine("Bokningen har uppdaterats.");
        io.printLine(booking.toString());
    }

    public void printNoBookingFoundToUpdate() {
        io.printLine("Ingen bokning hittades att uppdatera.");
    }

    //---------------------------InfoText------------------------------
    public void printBesiktningChoice() {
        io.printLine("Du har valt: Besiktning");
    }

    public void printReparationChoice() {
        io.printLine("Du har valt: Reparation");
    }

    public void printServiceChoice() {
        io.printLine("Du har valt: Service");
    }

    public void printBookingDetails(String bookingText) {
        io.printLine(bookingText);
    }

    //---------------------------MenyText------------------------------
    public void printServiceChoices() {
        io.printLine("\u001B[34m=========================\u001B[0m");
        io.printLine("       Välj tjänst       ");
        io.printLine("\u001B[34m=========================\u001B[0m");
        io.printLine("1. Besiktning");
        io.printLine("2. Reparation");
        io.printLine("3. Service");
        io.printLine("0. Avsluta");
        io.printLine("\u001B[34m=========================\u001B[0m");
        io.print("Val: ");
    }

    public void printMenyChoices() {
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("      Bilmeckarna AB");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("1. Skapa bokning");
        io.printLine("2. Visa alla bokningar");
        io.printLine("3. Sök bokning");
        io.printLine("4. Uppdatera bokning");
        io.printLine("5. Ta bort bokning");
        io.printLine("6. <- Gå tillbaka");
        io.printLine("0. Avsluta");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.print("Val: ");
    }

    public void printMenyChoicesRepair() {
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("      Bilmeckarna AB");
        io.printLine("       [Reparation]");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("1. Skapa bokning");
        io.printLine("2. Visa alla bokningar");
        io.printLine("3. Sök bokning");
        io.printLine("4. Uppdatera bokning");
        io.printLine("5. Ta bort bokning");
        io.printLine("6. <- Gå tillbaka");
        io.printLine("0. Avsluta");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.print("Val: ");
    }

    public void printMenyChoicesInspection() {
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("      Bilmeckarna AB");
        io.printLine("       [Besiktning]");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("1. Skapa bokning");
        io.printLine("2. Visa alla bokningar");
        io.printLine("3. Sök bokning");
        io.printLine("4. Uppdatera bokning");
        io.printLine("5. Ta bort bokning");
        io.printLine("6. <- Gå tillbaka");
        io.printLine("0. Avsluta");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.print("Val: ");
    }

    public void printMenyChoicesService() {
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("      Bilmeckarna AB");
        io.printLine("        [Service]");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("1. Skapa bokning");
        io.printLine("2. Visa alla bokningar");
        io.printLine("3. Sök bokning");
        io.printLine("4. Uppdatera bokning");
        io.printLine("5. Ta bort bokning");
        io.printLine("6. <- Gå tillbaka");
        io.printLine("0. Avsluta");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.print("Val:");
    }

    public void printExitProgram() {
        io.printLine("Avslutar programmet...Hejdå!");
    }

    public void printDefaultMenuChoice() {
        io.printLine("Ogiltigt val, försök igen!");
    }

    //---------------------------InputHandler------------------------------
    public void printEmptyNameNotAllowed() {
        io.printLine("Namn får inte vara tomt, försök igen!");
    }

    public void printWrongEmail() {
        io.printLine("Ogiltig e-post, försök igen!");
    }

    public void printWrongPhoneNumber() {
        io.printLine("Ogiltigt telefonnummer (endast siffror, 6-15 tecken!");
    }

    public void printWrongRegNumber() {
        io.printLine("Ogiltigt registreringsnummer, försök igen!");
    }

    public void printModelCantBeEmpty() {
        io.printLine("Modell får inte vara tom, försök igen!");
    }

    public void printWrongYearModel() {
        io.printLine("Ogiltig årsmodell, försök igen!");
    }

    public void printWrongNumberInput() {
        io.printLine("Du måste skriva in siffror.");
    }

    public void printWrongDate() {
        io.printLine("Ogiltigt datumformat, försök igen!");
    }

    public void printInputDate() {
        io.printLine("Ange datum för önskad bokning (ÅÅÅÅ-MM-DD): ");
    }

    public void printIdMustBeGreaterThanZero() {
        io.printLine("ID måste vara större än 0.");
    }

    public void printIdMustBeNumbers() {
        io.printLine("Ogiltigt ID, ID måste vara siffror.");
    }

    public void printStateNewStatus() {
        io.printLine("Ange ny status (DONE / NOT_DONE");
    }

    public void printStatusNotCorrect() {
        io.printLine("Ogiltigt val, skriv DONE eller NOT_DONE.");
    }

    public void printBackSpaceChoice() {
        io.printLine("<- Gå tillbaka.");
    }

    public void printUnkownChoice() {
        io.printLine("Ogiltigt val, försök igen.");
    }

    //bookingMenu
    public void printChooseBookingMenu() {
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("       Välj tjänst");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.printLine("1. Besiktning. ");
        io.printLine("2. Service. ");
        io.printLine("3. Reparation. ");
        io.printLine("0 Tillbaks. ");
        io.printLine("\u001B[34m==========================\u001B[0m");
        io.print("Val: ");
    }

    public void askForRegistrationNumber() {
        io.printLine("Ange registreringsnummer ");
    }

    public void askForModel() {
        io.printLine("Ange bilmärke ");
    }

    public void askForYearModel() {
        io.printLine("Ange årsmodell ");
    }

    public void askForName() {
        io.printLine("Ange kundens namn ");
    }

    public void askForPhoneNumber() {
        io.printLine("Ange kundens telefonnummer. ");
    }

    public void askForEmail() {
        io.printLine("Ange kundens email ");
    }

    public void askForBookingId() {
        io.printLine("Ange boknings-ID:");
    }

    public void askForRepairPrice() {
        io.printLine("Ange slutpris för reparationen: ");
    }

    public void printCompleteRepairTitle() {
        io.printLine("\u001B[33m==============================\u001B[0m");
        io.printLine("Avsluta Reparation & Sätt pris");
        io.printLine("\u001B[33m==============================\u001B[0m");
    }

    public void printSortOptions() {
        io.printLine("\u001B[33m==========================\u001B[0m");
        io.printLine("      Sortera");
        io.printLine("\u001B[33m==========================\u001B[0m");
        io.printLine("1. Sortera efter ID");
        io.printLine("2. Sortera efter Datum");
        io.printLine("3. Sortera efter Status");
        io.printLine("\u001B[33m==========================\u001B[0m");
        io.print("Val: ");
    }



    public void printUpdateBookingOptions() {
        io.printLine("\n === Redigera ===");
        io.printLine("1. Namn");
        io.printLine("2. Telefonnummer");
        io.printLine("3. Email");
        io.printLine("4. Bilmodell");
        io.printLine("5. Registreringsnummer");
        io.printLine("6. Bil årsmodell");
        io.printLine("7. Datum för bokning");
        io.printLine("8. Status");
        io.printLine("9. <- Gå tillbaka");
        io.printLine("10. Avsluta");
    }
    public void printSendMail(String email, String message) {
        io.printLine("Mejl skickat till kund (" + email + "):");
        io.printLine(message);
    }




}

