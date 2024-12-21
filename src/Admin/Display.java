package Admin;

import Account.*;
import Traffic_Officer.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

public class Display {

    public static String validateEmail() {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String email;
        while (true) {
            System.out.print("Enter  Email: ");
            email = Admin.cin.nextLine();
            if (!email.matches(emailRegex)) {
                System.out.println("Invalid email format. Please try again.");
                continue;
            }
            if (Account.Emails.contains(email)) {
                System.out.println("This email already exists. Please enter a different email.");
                continue;
            }
            break;
        }
        return email;
    }

    public static void singup(ArrayList<Owner> arr) {
        char c;
        do {
            String ID, Email, Password, Name, Contact_info;
            ID = Exc.Unique("Enter your ID: ", Account.ids);
            Email = validateEmail();
            out.print("Enter your Password: ");
            Password = Admin.cin.nextLine();
            Password = Exc.valid(Password);
            Name = Exc.Unique("Enter your Name: ", Account.Names);
            out.print("Enter your Contact_info: ");
            Contact_info = Admin.cin.nextLine();
            Contact_info = Exc.valid(Contact_info);
            arr.add(new Owner(ID, Name, Email, Password, Contact_info));
            out.println("Account created successfully!");
            out.print("Do you want to create another account? (y/n): ");
            c = Admin.cin.next().charAt(0);
            Admin.cin.nextLine();
        } while (c == 'y' || c == 'Y');
        out.println("Have a nice day!");

    }
    public static String Login(ArrayList<TrafficOfficer> TrafficOfficers, ArrayList<Admin> Admins, ArrayList<Owner> owners) {
        Scanner z = new Scanner(System.in);
        int count = 0;
        char c;
        boolean b = false;
        do {
            String name;
            String pass;
            out.print("Enter username: ");
            name = z.nextLine();
            out.print("Enter Password: ");
            pass = z.nextLine();
            for (int i = 0; i < Admins.size(); i++) {
                if (Admins.get(i).Name.equals(name) && Admins.get(i).comparePassword(pass)) {
                    return name + "," + i + "," + 1;
                }
            }
            for (int i = 0; i < TrafficOfficers.size(); i++) {
                if (TrafficOfficers.get(i).Name.equals(name) && TrafficOfficers.get(i).comparePassword(pass)) {
                    return name + "," + i + "," + 2;
                }
            }
            for (int i = 0; i < owners.size(); i++) {
                if (owners.get(i).Name.equals(name) && owners.get(i).comparePassword(pass)) {
                    return name + "," + i + "," + 3;
                }
            }
            count++;
            out.println("Invalid username or password. Attempt " + count + "/5");
            out.print("Do you want to continue: ");
            c = z.next().charAt(0);
            if (c == 'n' || c == 'N') {
                b = true;
                break;
            }
            z.nextLine();
        } while (count < 5);
        if (!b) {
            System.out.println("No attempts are available. Please sign up.");
            Display.singup(owners);
        }
        return null;
    }
}

