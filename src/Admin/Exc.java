package Admin;

import java.util.ArrayList;

import static java.lang.System.out;

public class Exc {
    public static int infinite(int num, int max, int min) {
        String input;
        while (true) {
            input = Admin.cin.nextLine();
            if (input.matches("\\d+")) {  // Check if the input contains only digits
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
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            } else {
                System.out.println("Invalid input ✕.\nPlease enter a valid number: ");
            }
        }

    }

    public static void Arr(ArrayList<?> arr, int index) {
        while (true) {
            System.out.println("Enter an index to access the list: ");
            index = Admin.cin.nextInt();
            try {
                if (index >= 0 && index < arr.size()) {
                    System.out.println("Item at index " + index + ": " + arr.get(index));
                    return;
                } else {
                    System.out.println("Invalid index. Please try again.");
                }
            } catch (IndexOutOfBoundsException exp) {
                System.out.println("Invalid index. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        int x = 5;
        ArrayList<String> arr = new ArrayList<>(x);
        arr.add("a");
        arr.add("b");
        arr.add("c");
        arr.add("d");
        arr.add("e");
        arr.add("f");
        arr.add("g");
        int z = 0;
        Arr(arr, z);
    }
}


