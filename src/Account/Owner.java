/*package Account;

public class Owner extends Account {

    public String Contact_info;
    Boolean isVerified = false; //from admin
    //list of vehicles
    //list of messages
    Owner(String ID, String Email, String Password, String Name, String Contact_info) {
        super(ID, Email, Password, Name);
        this.Contact_info = Contact_info;

    }
}*/
package Account;

import Admin.*;
import Vehicle.*;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Owner extends Account {

    //  contact number of the owner
    // boolean isVerified = false; // Verification status (set by the admin)
  //  ArrayList<String> vehicles; // List to store the IDs of vehicles ( owned by this owner )
    public ArrayList<Notification> notifications = new ArrayList<>(); // List to store notifications
    public ArrayList<Vehicle> vehicle = new ArrayList<>();
    // 0# The Constructor
public static int ownerscount=0;
    public Owner(String id, String Name, String email, String Password, String Contact_info) {
        super(id, email, Password, Name);
        this.Contact = Contact_info;
ownerscount++;
    }

    // 1# Method to add a vehicle to the owner's list


    // 3# Method to retrieve all vehicles owned by the owner



    // 4# Method to add a message to the owner's messages
    public int NumOfNotseen() {
        // Method to get the number of unseen notifications
        int count = 0;
        for (Notification notification : notifications) {
            if (!notification.isRead) {
                count++;
            }
        }
        return count;
    }


    // 5# Method to retrieve all messages sent to the owner

    // Method to display owner details

    @Override
    public void getInfo(){
        System.out.println("Owner Name: " + Name);
        System.out.println("Contact Number: " + Contact);
        System.out.println("Email: " + Email);
    }

    public static void OwnerPage(ArrayList<Owner> Owners, int index) {
        // Method to display the owner page
        Scanner input = new Scanner(System.in);
        out.println("Welcome, " + Owners.get(index).Name + "!");

        boolean logout = false;
        do {

            out.println("You have " + Owners.get(index).NumOfNotseen() + " unseen notifications.");
            out.println("1. View Profile");//✓
            out.println("2. View Your Notifications");//✓
            out.println("3. View Your Vehicles");//✓
            out.println("4. Add Vehicle");//✓
            // out.println(". Pay Fine"); // in view vehicles
            out.println("5. Change Password");//✓
            out.println("6. Log Out"); //✓
            out.print("Enter your choice: ");
            int choice = 0;
            choice = Exc.infinite(1, 6, 1);
            switch (choice) {
                case 1:
                    Owners.get(index).getInfo();
                    break;
                case 2:
                    if (Owners.get(index).notifications.isEmpty()) {
                        out.println("You have no notifications.");
                        break;
                    }
                    Notification.showNotification(Owners.get(index).notifications, Owners.get(index).NumOfNotseen());
                    break;
                case 3:
                    boolean existFine = false;
                    if (Owners.get(index).vehicle.isEmpty()) {
                        out.println("You have no vehicles.");
                        break;
                    }
                    for (int i = 0, j = 0; i < Owners.get(index).vehicle.size(); i++) {
                        j = i + 1;
                        out.println("#" + j + ":-");
                        Owners.get(index).vehicle.get(i).getData();
                        if (Owners.get(index).vehicle.get(i).TV.isEmpty()) {
                            existFine = true;
                        }
                        out.println("_________________________");
                    }

                    if (existFine) {
                        out.println("Do you want to view a fine? (Y/N)");

                        char ch;
                        do {
                            ch = input.next().charAt(0);
                            if (ch == 'N' || ch == 'n') break;

                            int vehicleIndex = 0;
                            out.println("Enter the number of the vehicle you want to view the fines for:");
                            vehicleIndex = Exc.infinite(1, Owners.get(index).vehicle.size(), 1);
                            Owners.get(index).vehicle.get(vehicleIndex - 1).PayFine();

                            out.println("Do you want to view another vehicle's fines? (Y/N)");
                        } while (ch == 'Y' || ch == 'y');
                    }

                    break;
                case 4:
                    Vehicle.addVehicles(Owners.get(index).vehicle, Owners.get(index).Name);
                    break;
                case 5:
                    Owners.get(index).changePass();
                    break;
                case 6:
                    logout = true;
                    out.println("Logging out...");
                    break;

            }
            System.out.println("----------------------------");
        } while (!logout);
        System.out.println("===============================================\n");
    }
    public  void sendNotification(Traffic_Violation tv) {
        String[] parts = new String[6];
        parts[0] = tv.getVehicle_ID();
        parts[1] = tv.getDate();
        parts[2] = tv.getWhoIssued();
        parts[3] = tv.getZoneName();
        parts[4] = tv.getViolationID();
        parts[5] = tv.getFine_amount()+"";
        String message=  Notification.StandardMessage(Name,tv);
        Notification notification = new Notification(message,parts);
        notifications.add(notification);
    }
}
