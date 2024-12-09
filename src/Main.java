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


public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void hide_pass() {
        Console console = System.console();

        if (console == null) {
            System.out.println("No console available");
            return;
        }

        // قراءة كلمة المرور باستخدام console.readPassword() لإخفائها
        char[] passwordArray = console.readPassword("Enter your password: ");

        // تحويل كلمة المرور إلى سلسلة نصية
        String password = new String(passwordArray);

        System.out.println("You entered a password of length: " + password.length());
        System.out.println("The password is: " + password);
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Admin> Admins = new ArrayList<>();
        ArrayList<TrafficOfficer> TrafficOfficers = new ArrayList<>();
        ArrayList<Owner> owners = new ArrayList<>();
        ArrayList<Zone> zones = new ArrayList<>();

        File_Processing.Load_Accounts(Admins, TrafficOfficers, owners);
        File_Processing.Load_Zones(zones);
        Admin.AdminPage(Admins,0, owners,TrafficOfficers, zones, Traffic_Violation.tra, "" );
        TrafficOfficer.OfficerPage(TrafficOfficers, 1, owners);
        /*
        * ArrayList<Admin> admin, int index, ArrayList<Owner> owner, ArrayList<TrafficOfficer> TrafficOfficer, ArrayList<Zone> Zone,  ArrayList<Traffic_Violation> traffic_Violation, String by
        * */
        File_Processing.Save_Accounts(Admins, TrafficOfficers, owners);
        /*

         */


    }

}