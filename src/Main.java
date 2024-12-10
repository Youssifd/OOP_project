import Admin.*;
import Area.*;
import File_function.File_Processing;
import Traffic_Officer.TrafficOfficer;
import Vehicle.*;
import Account.Owner;

import java.io.Console;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.System.out;


public class Main {
    public static Scanner input = new Scanner(System.in);

    public static String hide_pass() {
        Console console = System.console();
        String password = " ";
        if (console == null) {
            System.out.println("No console available");
            return password;
        }

        // قراءة كلمة المرور باستخدام console.readPassword() لإخفائها
        char[] passwordArray = console.readPassword("Enter your password: ");

        // تحويل كلمة المرور إلى سلسلة نصية
        password = new String(passwordArray);

        return password;
    }

    public static void main(String[] args) {
        ArrayList<Admin> Admins = new ArrayList<>();
        ArrayList<TrafficOfficer> TrafficOfficers = new ArrayList<>();
        ArrayList<Owner> owners = new ArrayList<>();
        ArrayList<Zone> zones = new ArrayList<>();


        File_Processing.Load_Accounts(Admins, TrafficOfficers, owners, zones);
        File_Processing.Load_Zones(zones);
        /*Admin.AdminPage(Admins, 0, owners, TrafficOfficers, zones, Traffic_Violation.tra, "");
        TrafficOfficer.OfficerPage(TrafficOfficers, 1, owners,zones);*/
        Owner.OwnerPage(owners, 0);
        File_Processing.Save_Accounts(Admins, TrafficOfficers, owners);


        String s = Display.Login(TrafficOfficers, Admins, owners);

        if (s != null) {
            String[] parts = s.split("-");
            String name = parts[0];
            int index = Integer.parseInt(parts[1]);
            int type = Integer.parseInt(parts[2]);

            switch (type) {
                case 1 -> out.println("Welcome Admin: " + name);
                case 2 -> out.println("Welcome Traffic Officer: " + name);
                case 3 -> out.println("Welcome Owner: " + name);
                default -> out.println("Unknown role.");
            }
        }
    }
}