package org.example.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getIntInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Rensa newline
                return input;
            } catch (InputMismatchException e) {
                System.out.print("Ogiltig inmatning. Ange en siffra: ");
                scanner.nextLine(); // Rensa felaktig input
            }
        }
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
