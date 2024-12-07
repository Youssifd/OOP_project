package Admin;
import Account.*;
import Area.Zone; // Ensure that the 'Zone' class is defined in the 'Area' package and accessible from this module.
import Traffic_Officer.TrafficOfficer;

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;
public class Admin extends Account {
    public static int Admincounter =0;
    public String Contact;

    public static Scanner cin=new Scanner(System.in);
    public Admin(String id,String Name,String email,String Passowrd,String Contact){
        super(id, email, Passowrd, Name);
        Admincounter++;
        this.Contact=Contact;
    }

    public ArrayList<TrafficOfficer> AddOfficer(ArrayList<TrafficOfficer> arr){
        char c;
        String Id,  Name, Email, Password, Contact,  assignedZone;
        do {
            out.print("Enter your ID: ");
          Id=TrafficOfficer.UniqueID(arr);
            out.print("Enter your Email: ");
            Email=Admin.cin.nextLine();
            out.print("Enter your Password: ");
            Password=Admin.cin.nextLine();
            out.print("Enter your Name: ");
            Name=Admin.cin.nextLine();
            out.print("Enter your Contact_info: ");
            Contact=Admin.cin.nextLine();
            out.print("Enter your assigned zone: ");
            assignedZone=Admin.cin.nextLine();
            arr.add(new TrafficOfficer(Id,Email,Password,Name,Contact,assignedZone));
            out.println("Account created successfully!");
            out.print("Do you want to create another account? (y/n): ");
            c=Admin.cin.next().charAt(0);
        } while (c=='y' || c=='Y' );
        out.println("This offeceres are registered by Admin: " + this.Name);
        return arr;
    }
    public static  void addZone(ArrayList<Zone> zones) {
        out.println("Enter the name of the zone: ");
        String name = cin.nextLine();
        out.println("Enter the Location of the zone: ");
        String Loc = cin.nextLine();
        Zone zone = new Zone(name,Loc);
        zones.add(zone);
        out.println("Zone ID: "+zone.getID()+",added successfully!");
    }
    public ArrayList<Admin> addAdmins(ArrayList<Admin> admins, ArrayList<Owner> owners) {
        String id, name, email, password, contact;
        char hasAccount, continueAdding;

        System.out.print("Does this Admin already have an account? (y/n): ");
        hasAccount = Admin.cin.next().charAt(0);
        Admin.cin.nextLine(); // Consume the leftover newline character.

        if (hasAccount == 'y' || hasAccount == 'Y') {
            System.out.print("Enter their ID: ");
            id = Admin.cin.nextLine();

            for (Owner owner : owners) {
                if (owner.getID().equals(id)) {
                    name = owner.Name;
                    email = owner.Email;
                    contact = owner.Contact;
                    System.out.print("Enter their password: ");
                    password = Admin.cin.nextLine();

                    admins.add(new Admin(id, name, email, password, contact));
                    System.out.println("Admin added successfully!");
                    return admins;
                }
            }
            System.out.println("No owner found with the given ID.");
        } else {
            System.out.println("This Admin does not have an account.");
            do {
                id = UniqueID(admins);
                System.out.print("Enter your Email: ");
                email = Admin.cin.nextLine();
                System.out.print("Enter your Password: ");
                password = Admin.cin.nextLine();
                System.out.print("Enter your Name: ");
                name = Admin.cin.nextLine();
                System.out.print("Enter your Contact_info: ");
                contact = Admin.cin.nextLine();

                admins.add(new Admin(id, name, email, password, contact));
                System.out.println("Account created successfully!");

                System.out.print("Do you want to create another account? (y/n): ");
                continueAdding = Admin.cin.next().charAt(0);
                Admin.cin.nextLine(); // Consume the leftover newline character.
            } while (continueAdding == 'y' || continueAdding == 'Y');
        }
        return admins;
    }
    private String UniqueID(ArrayList<Admin> accounts) {
        String id;
        System.out.print("Enter a unique ID: ");
        while (true) {
            id = Admin.cin.nextLine();
            String finalId = id;
            boolean isUnique = accounts.stream().noneMatch(admin -> admin.getID().equals(finalId));

            if (isUnique) {
                break;
            }
            System.out.println("This ID is already taken. Please enter a different ID: ");
        }
        return id;
    }

    @Override
    public String toString() {
        return "Admin name: " + Name;
    }

}
