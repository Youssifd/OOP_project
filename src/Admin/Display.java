package Admin;

import Account.*;
import Traffic_Officer.*;
import Area.*;
import Vehicle.*;

import java.util.ArrayList;


import static java.lang.System.out;

public class Display {
    public static String validateEmail() {
        // التعبير النمطي للتحقق من صحة البريد الإلكتروني
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        String email;

        while (true) {
            // طلب البريد الإلكتروني من المستخدم
            System.out.print("Enter your Email: ");
            email = Admin.cin.nextLine();

            // التحقق من صحة صيغة البريد الإلكتروني
            if (!email.matches(emailRegex)) {
                System.out.println("Invalid email format. Please try again.");
                continue; // إعادة طلب الإدخال من المستخدم
            }

            // التحقق من أن البريد الإلكتروني غير موجود مسبقًا
            if (Account.Emails.contains(email)) {
                System.out.println("This email already exists. Please enter a different email.");
                continue; // إعادة طلب الإدخال من المستخدم
            }

            // إذا كان البريد الإلكتروني صحيحًا وغير موجود مسبقًا
            break;
        }

        return email;
    }

    public static ArrayList<Owner> singup(ArrayList<Owner> arr) {
        char c;
        do {
            String ID, Email, Password, Name, Contact_info;
            ID = Account.Unique("Enter your ID: ", Account.ids);

            Email = validateEmail();
        out.print("Enter your Password: ");
            Password = Admin.cin.nextLine();
           

                 Name =  Account.Unique("Enter your Name: ", Account.Names);

            out.print("Enter your Contact_info: ");
            Contact_info = Admin.cin.nextLine();
            arr.add(new Owner(ID, Name,  Email,Password, Contact_info));
            out.println("Account created successfully!");
            out.print("Do you want to create another account? (y/n): ");
            c = Admin.cin.next().charAt(0);
            Admin.cin.nextLine();
        } while (c == 'y' || c == 'Y');
        out.println("Have a nice day!");
        return arr;
    }

    public  static  String Login(ArrayList<TrafficOfficer> TrafficOfficers, ArrayList<Admin> Admins, ArrayList<Owner> owners) {
        int count = 0;

        do {
            String name;
            String pass;
            out.print("Enter username: ");
            name = Admin.cin.nextLine();
            out.print("Enter Password: ");
            pass = Admin.cin.nextLine();

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

        } while (count < 5);
Display.singup(owners);
return null;
    }


    public static void main(String[] args) {
        ArrayList<TrafficOfficer> TrafficOfficers = new ArrayList<>();
        ArrayList<Admin> Admins = new ArrayList<>();
        ArrayList<Owner> owners = new ArrayList<>();

        Admins.add(new Admin("1", "Marwan", "<EMAIL>", "MS", "123"));
        owners.add(new Owner("2", "<EMAIL>", "N", "you", "MS"));
        TrafficOfficers.add(new TrafficOfficer("3", "MM", "<EMAIL>", "M", "w", "555555555555555"));

Display.singup(owners);
        Display display = new Display();
        String s = display.Login(TrafficOfficers, Admins, owners);

        if (s != null) {
            String[] parts = s.split(",");
            String name = parts[0];
            int index = Integer.parseInt(parts[1]);
            int type = Integer.parseInt(parts[2]);
            switch (type) {
                case 1 -> out.println("Welcome Admin: " + name);
                case 2 -> out.println("Welcome Traffic Officer: " + name);
                case 3 -> out.println("Welcome Owner: " + name);
                default -> out.println("Unknown role.");
            }
        } else {
            out.println("Login failed after 5 attempts.");
        }
    }
}

