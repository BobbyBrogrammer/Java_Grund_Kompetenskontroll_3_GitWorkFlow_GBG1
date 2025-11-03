package org.example.systemIO;

import java.util.Scanner;

public class SystemIO implements IIO {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public String readLine() {return sc.nextLine();}
    @Override
    public void printLine(String message) {
        System.out.println(message);
    }
}
