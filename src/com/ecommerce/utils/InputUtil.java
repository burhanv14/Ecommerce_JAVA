package com.ecommerce.utils;

import java.util.Scanner;

public class InputUtil {
    public static int getIntInput(Scanner scanner) {
        int input = -1;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        return input;
    }
}
