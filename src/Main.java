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


        File_Processing.Load_Zones(zones);
        File_Processing.Load_Accounts(Admins, TrafficOfficers, owners);
        File_Processing.Load_Notifications(owners);

        //turn_on_traffic_lights
        for(Zone zone: zones){
            for(Traffic_Lights traffic_light: zone.traffic_lights){
                traffic_light.Start();
            }
          }

        out.println("========================================================================");
        out.println("\t\t Welcome to Traffic Management System :) ");
        out.println("\t\t=========================================");
        while (true) {
            out.println("1- Sign up\n2- Login\n3- Exit");
            out.print("Enter your choice: ");
            int choice = 0;
            choice = Exc.infinite(choice, 3, 1);
            if (choice == 1) {
                Display.singup(owners);
            } else if (choice == 2) {

                String s = Display.Login(TrafficOfficers, Admins, owners);

                if (s != null) {
                    int index=-1;
                    int type=-1;
                    String[] parts = s.split(",");
                    String name = parts[0];
                    try {
                     index = Integer.parseInt(parts[1]);
                     type = Integer.parseInt(parts[2]);

                    }catch (NumberFormatException e){
                        out.println("Error: Invalid return");
                        out.println("Exiting...");
                        System.exit(0);
                    }

                    switch (type) {
                        case 1 ->
                                Admin.AdminPage(Admins, index, owners, TrafficOfficers, zones, Traffic_Violation.tra);
                        case 2 -> TrafficOfficer.OfficerPage(TrafficOfficers, index, owners, zones);
                        case 3 -> Owner.OwnerPage(owners, index);
                        default -> out.println("Unknown role.");
                    }
                }
            } else if (choice == 3) {

                //turn_off_traffic_lights
                for (Zone zone : zones) {
                    for (Traffic_Lights traffic_light : zone.traffic_lights) {
                        traffic_light.Stop();
                    }
                }

                File_Processing.Save_Zones(zones);
                File_Processing.Save_Accounts(Admins, TrafficOfficers, owners);
                File_Processing.Save_Notifications(owners);
                out.println("Exiting...");
                System.exit(0);
            }
        }
    }
}
