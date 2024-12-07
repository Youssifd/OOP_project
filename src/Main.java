import Admin.*;
import Area.*;
import File_function.File_Processing;
import Traffic_Officer.TrafficOfficer;
import Vehicle.*;
import Account.Owner;
import java.io.Console;
import java.util.ArrayList;

import java.util.Scanner;


public class Main {
    public static Scanner input = new Scanner(System.in);
    public static  void hide_pass(){
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

    public static void AdminPage(ArrayList<Zone> zones/*,ArrayList<officer_class>*/, String AccName) {
        System.out.println("Welcome " + AccName);
        System.out.println("1- Add Zone\n2- modify Zone\n3- Traffic Report\n4- Add new admin\n5- Logout");
        //6- inbox for messages
        int choice = 0;
        choice = Exc.infinite(choice, 1, 1);
        switch (choice) {
            case 1:
                Admin.addZone(zones);
                break;

            case 2:
                boolean found = false;
                System.out.println("Enter the zone ID: ");
                String zoneID = input.nextLine();
                for (int i = 0; i < zones.size(); i++) {

                    if (zones.get(i).getID().equals(zoneID)) {
                        found = true;
                        System.out.println("1- Add a traffic light\n2- Remove a traffic light\n3- Edit a traffic light");
                        choice = Exc.infinite(choice, 3, 1);
                        switch (choice) {
                            case 1:
                                zones.get(i).addTrafficLight();
//                        zone.addTrafficLight();
                                break;
                            case 2:
                                zones.get(i).removeTrafficLight();
//                        zone.removeTrafficLight();
                                break;
                            case 3:
                                zones.get(i).editTrafficLight();
//                        zone.editTrafficLight();
                                break;
                        }
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Zone not found!");
                    break;
                }
                break;
            case 3:
                //Traffic Report
                break;
            case 4:
                //Add new admin
                break;
            case 5:
                //Logout
                break;

        }
    }

    public static void main(String[] args) {
        ArrayList<Admin>    Admins = new ArrayList<>();
        ArrayList<TrafficOfficer> TrafficOfficers = new ArrayList<>();
        ArrayList<Owner> owners = new ArrayList<>();
        /*Admins.add(new Admin("1", "Marwan", "<EMAIL>", "MS", "123"));
        owners.add(new Owner("2", "<EMAIL>", "N", "you", "MS"));
        TrafficOfficers.add(new TrafficOfficer("3", "MM", "<EMAIL>", "M", "w", "555555555555555"));
        owners.get(0).vehicle.add(new Vehicle( "1", "1", "1", "1"));
        owners.get(0).vehicle.get(0).TV.add(new Traffic_Violation("1", "1", "1", "1", 1, "1"));
*/
        File_Processing.Load_Accounts(Admins, TrafficOfficers, owners);
        File_Processing.Save_Accounts(Admins, TrafficOfficers, owners);


    }

}