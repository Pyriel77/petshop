package com.codecool.petshop.view;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner;
    PrintStream out;

    public UserInterface(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    public void println(Object obj) {
        out.println(obj);
    }

    public void printTitle(String title) {
        out.println("\n -- " + title + " --");
    }

    public void printOption(char option, String description) {
        out.println("(" + option + ")" + " " + description);
    }

    public char choice(String options) {
        // Given string options -> "abcd"
        // keep asking user for input until one of provided chars is provided
        String input = scanner.nextLine();
        while (!options.contains(input) || input.length() != 1) {
            input = scanner.nextLine();
        }
        return input.charAt(0);
    }

    public String readString(String prompt, String defaultValue) {
        // Ask user for data. If no data was provided use default value.
        // User must be informed what the default value is.
        out.printf("%s (default value is %s):\n", prompt, defaultValue);
        try {
            String input = scanner.nextLine();
            return input.isEmpty() ? defaultValue : input;
        } catch (NoSuchElementException e) {
            return defaultValue;
        }
    }

    public Date readDate(String prompt, Date defaultValue) {
        // Ask user for a date. If no data was provided use default value.
        // User must be informed what the default value is.
        // If provided date is in invalid format, ask user again.
        while(true) {
            out.printf("%s (default value is %s):\n", prompt, defaultValue);
            try {
                String dateString = scanner.nextLine();
                if (dateString.isEmpty()) {
                    return defaultValue;
                }
                return Date.valueOf(dateString);
            } catch (IllegalArgumentException e) {
                out.println("Invalid format!");
            }
        }
    }

    public int readInt(String prompt, int defaultValue) {
        // Ask user for a number. If no data was provided use default value.
        // User must be informed what the default value is.
        out.printf("%s (default value is %d):\n", prompt, defaultValue);
        try {
            String result = scanner.nextLine();
            return Integer.parseInt(result);
        } catch (NoSuchElementException|NumberFormatException e) {
            return defaultValue;
        }
    }

}
