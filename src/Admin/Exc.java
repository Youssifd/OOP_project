package Admin;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class Exc {
    public static int infinite(int num, int max, int min) {
        String input;
        Scanner cin = new Scanner(System.in);
        while (true) {
            input = cin.nextLine();
            // Check if the input contains only digits
            try {
                num = Integer.parseInt(input);
                if (num >= min && num <= max) {
                    return num;
                } else {
                    out.println("-------------------");
                    out.println("Invalid Choice!");
                    out.print("Enter a valid choice: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input ✕.\n" +
                        "Please enter a valid number: ");
            }
        }

    }

    public static String Unique(String prompt, ArrayList<String> existingValues) {
        String input;
        System.out.print(prompt);
        while (true) {
            input = Admin.cin.nextLine();

            // Check if input contains a comma

            if (input.contains(",")) {
                System.out.println("Error: The value cannot contain a comma. Please try again.");
                System.out.print("Enter new value: ");
                continue;
            } else {
                if (existingValues.contains(input)) {
                    System.out.println("Error: This value is already used. Please try again.");
                    System.out.print("Enter new value: ");
                }
                // Valid input
                else {
                    existingValues.add(input);
                    break;
                }
            }
        }
        return input;

        // Check if input is unique

    }

    public static String vald(String input) {
        while (true) {
            if (input.contains(",")) {
                System.out.println("Error: The value cannot contain a comma. Please try again.");
                System.out.print("Enter new value: ");
                input = Admin.cin.nextLine(); // Assuming Admin.cin is a valid Scanner object
            } else {
                return input;
            }
        }
    }


}


