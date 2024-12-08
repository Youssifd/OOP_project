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
    Boolean isVerified = false; // Verification status (set by the admin)
    ArrayList<String> vehicles; // List to store the IDs of vehicles ( owned by this owner )
    ArrayList<String> messages; // List to store messages sent to the owner
    public ArrayList<Vehicle> vehicle = new ArrayList<>();
    // 0# The Constructor

    public Owner(String id,String Name,String email,String Password,String Contact_info) {
        super(id, email, Password, Name);
        this.Contact = Contact_info;
        this.vehicles = new ArrayList<>(); // Initialize the list of vehicles
        this.messages = new ArrayList<>(); // Initialize the list of messages
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

    public void addMessage(String message) {
        messages.add(message);
    }

    // 5# Method to retrieve all messages sent to the owner

    public ArrayList<String> getMessages() {
        return messages;
    }
    public static String UniqueID(ArrayList <Owner> accounts) {
        String id;
        System.out.print("Enter a unique ID: ");
        while (true) {
            id = Admin.cin.nextLine();
            String finalId = id;
            boolean isUnique = accounts.stream().noneMatch(owner -> owner.getID().equals(finalId));

            if (isUnique) {
                break;
            }
            System.out.println("This ID is already taken. Please enter a different ID: ");
        }
        return id;
    }


    protected void changePass(ArrayList<Owner> Owners,int index)
    {
        out.print("Enter your new password: ");
        Owners.get(index).Password=Admin.cin.nextLine();
        out.println("Password changed successfully!");
    }
    // Method to display owner details
    
    public void displayOwnerDetails() {
        System.out.println("Owner Name: " + Name);
        System.out.println("Contact Number: " + Contact);
        System.out.println("Email: " + Email);
        System.out.println("Verification Status: " + (isVerified ? "Verified" : "Not Verified"));
        System.out.println("Owned Vehicles: " + vehicles);
        System.out.println("Messages: " + messages);
    }
}
