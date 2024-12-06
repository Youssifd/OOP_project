import Admin.*;
import Area.Traffic_Lights;
import Area.Zone;
import File_function.File_Processing;
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
        ArrayList<Zone> zones = new ArrayList<>();
        File_Processing.Load_Zones(zones);
        for(Zone zone : zones) {
            zone.getinfo();
            for (Traffic_Lights traffic_light : zone.traffic_light) {
                traffic_light.Details();
            }
        }
        File_Processing.Save_Zones(zones);


    }

}