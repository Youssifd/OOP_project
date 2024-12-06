import Admin.Admin;
import Admin.Exc;
import Area.Zone;

import java.util.ArrayList;
import java.util.Scanner;


public class Draft {
    public static Scanner input = new Scanner(System.in);
    public static void AdminPage(ArrayList<Zone> zones/*,ArrayList<officer_class>*/, String AccName) {
        System.out.println("Welcome " + AccName);
        System.out.println("1- Add Zone\n2- modify Zone\n3- Traffic Report\n4- Add new admin\n5- Logout");
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
        /*if (choice == 1) {
            Admin.addZone(zones);
        }*/


    }
}

