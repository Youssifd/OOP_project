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
            if (input.contains(",")) {
                System.out.println("Error: The value cannot contain a comma. Please try again.");
                System.out.print("Enter new value: ");
            }
            else {
                if (existingValues.contains(input)) {
                    System.out.println("Error: This value is already used. Please try again.");
                    System.out.print("Enter new value: ");
                }
                else {
                    existingValues.add(input);
                    break;
                }
            }
        }
        return input;
    }
    public static String valid(String s)
    {
        Scanner scan;
        while (true)
        {
            scan=new Scanner(System.in);
            if (s.contains(",")) {
                System.out.println("Error: The value cannot contain a comma. Please try again.");
                System.out.print("Enter new value: ");
                s=scan.nextLine();
            }
            else
                return s;
        }


    }
}


