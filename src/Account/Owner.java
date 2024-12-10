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

import Admin.Admin;
import Vehicle.Vehicle;

import java.util.ArrayList;

import static java.lang.System.out;

public class Owner extends Account {

    //  contact number of the owner
   // boolean isVerified = false; // Verification status (set by the admin)
    ArrayList<String> vehicles; // List to store the IDs of vehicles ( owned by this owner )
     public ArrayList<Notification>  notifications = new ArrayList<>(); // List to store notifications
    public ArrayList<Vehicle> vehicle = new ArrayList<>();
    // 0# The Constructor

    public Owner(String id, String Name, String email, String Password, String Contact_info) {
        super(id, email, Password, Name);
        this.Contact = Contact_info;
        this.vehicles = new ArrayList<>(); // Initialize the list of vehicles

    }

    // 1# Method to add a vehicle to the owner's list

    public void addVehicle(String vehicleID) {
        vehicles.add(vehicleID);
    }

    // 2# Method to remove a vehicle from the owner's list

    public boolean removeVehicle(String vehicleID) {
        return vehicles.remove(vehicleID);
    }

    // 3# Method to retrieve all vehicles owned by the owner

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    // 4# Method to add a message to the owner's messages
    public  int getNumOfNotificationsUnseen(){
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

    protected void changePass(ArrayList<Owner> Owners, int index) {
        out.print("Enter your new password: ");
        Owners.get(index).Password = Admin.cin.nextLine();
        out.println("Password changed successfully!");
    }
    // Method to display owner details

    public void displayOwnerDetails() {
        System.out.println("Owner Name: " + Name);
        System.out.println("Contact Number: " + Contact);
        System.out.println("Email: " + Email);
        System.out.println("Owned Vehicles: " + vehicles);
         // System.out.println("Verification Status: " + (isVerified ? "Verified" : "Not Verified"));
        // System.out.println("Messages: " + messages);
    }
}
