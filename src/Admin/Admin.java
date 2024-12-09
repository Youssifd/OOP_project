package Admin;

import Account.*;

import Area.*; // Ensure that the 'Zone' class is defined in the 'Area' package and accessible from this module.
import Traffic_Officer.TrafficOfficer;
import Vehicle.Traffic_Violation;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class Admin extends Account {
    public static int Admincounter = 0;
    //  public String Contact;

    public static Scanner cin = new Scanner(System.in);

    public Admin(String id, String Name, String email, String Passowrd, String Contact) {
        super(id, email, Passowrd, Name);
        Admincounter++;
        this.Contact = Contact;
    }

    public ArrayList<TrafficOfficer> AddOfficer(ArrayList<TrafficOfficer> arr, ArrayList<Zone> Zone) {
        char c;
        String Id, Name, Email, Password, Contact, assignedZone;
        do {

            Id = Account.Unique("Enter your ID: ", Account.ids);
            out.print("Enter your Email: ");
            Email = Admin.cin.nextLine();
            out.print("Enter your Password: ");
            Password = Admin.cin.nextLine();
            out.print("Enter your Name: ");
            Name = Admin.cin.nextLine();
            out.print("Enter your Contact_info: ");
            Contact = Admin.cin.nextLine();
            for (int i = 0, j; i < Zone.size(); i++) {
                j = i + 1;
                out.println(j + "- " + Zone.get(i).getName());
            }
            out.print("Enter Number assigned zone: ");
            int choice = 0;
            choice = Exc.infinite(choice, Zone.size(), 1);
            assignedZone = Zone.get(choice - 1).getName();
            //String Id, String Name, String Email, String Password, String Contact, String assignedZone
            arr.add(new TrafficOfficer(Id, Name, Email, Password, Contact, assignedZone));
            out.println("Account created successfully!");
            out.print("Do you want to create another account? (y/n): ");
            c = Admin.cin.next().charAt(0);
        } while (c == 'y' || c == 'Y');
        out.println("This offeceres are registered by Admin: " + this.Name);
        return arr;
    }

    public static void addZone(ArrayList<Zone> zones) {
        out.println("Enter the name of the zone: ");
        String name = cin.nextLine();
        out.println("Enter the Location of the zone: ");
        String Loc = cin.nextLine();
        Zone zone = new Zone(name, Loc);
        zones.add(zone);
        out.println("Zone ID: " + zone.getID() + ",added successfully!");
    }

    public ArrayList<Admin> addAdmins(ArrayList<Admin> admins, ArrayList<Owner> owners) {
        String id, name = "", email, password, contact;
        char hasAccount, continueAdding;

        System.out.print("Does this Admin already have an account? (y/n): ");
        hasAccount = Admin.cin.next().charAt(0);
        Admin.cin.nextLine();

        if (hasAccount == 'y' || hasAccount == 'Y') {
            System.out.print("Enter their ID: ");
            id = Admin.cin.nextLine();

            for (Owner owner : owners) {
                if (owner.getID().equals(id)) {

                    name = Account.Unique("Enter their new username: ", Account.Names);

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
                id = Account.Unique("Enter their new username: ", Account.ids);
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

    protected void changePass(ArrayList<Admin> admins, int index) {
        out.println("Note that you will need to login again to use this feature and you can change your password only.");
        out.print("Enter your new password: ");
        admins.get(index).Password = Admin.cin.nextLine();
        out.println("Password changed successfully!");
    }

    public static void AdminPage(ArrayList<Admin> admin, int index, ArrayList<Owner> owner, ArrayList<TrafficOfficer> TrafficOfficer, ArrayList<Zone> Zone, ArrayList<Traffic_Violation> traffic_Violation, String by) {
        boolean logout = false;
        System.out.println("Welcome " + admin.get(index).Name + "!");
        do {
            System.out.println("1- Add traffic lights and configure durations.\n2- update traffic lights and configure durations.\3- delete traffic lights and configure durations.\n" +
                    "4- Add zone.\n5- View violations by vehicle or by zone.\n6- Generate traffic reports\n"
                    + "7- Add new Admin.\n8- change my password.\n9- Add new Traffic Officer.\n10- Logout.\n");
            int a = 0, i = 0;

            a = Exc.infinite(a, 10, 1);
            if (a >= 1 && a <= 3) {
                for (int x = 0, j; x < Zone.size(); x++) {
                    j = x + 1;
                    out.println(j + "- " + Zone.get(x).getName());
                    out.print("Enter Number assigned zone: ");
                    int choice = 0;
                    choice = Exc.infinite(choice, Zone.size(), 1);
                    i = choice - 1;
                }

            }

            switch (a) {
                case 1:
                    Zone.get(i).addTrafficLight();
                    break;
                case 2:
                    Zone.get(i).editTrafficLight();
                    break;
                case 3:
                    Zone.get(i).removeTrafficLight();
                    break;
                case 4:
                    Admin.addZone(Zone);
                    break;
                case 5://make global object of Traffic_Violation
                    Traffic_Violation.View_violations(traffic_Violation, by);// which violation-> Traffic_Violation.(traffic_Violation)static
                    break;
                case 6:
                    TrafficReport.generateFrequentViolationsReport(traffic_Violation);
                    break;
                case 7:
                    admin.get(index).addAdmins(admin, owner);
                    break;
                case 8:
                    admin.get(index).changePass(admin, index);
                    break;
                case 9:
                    admin.get(index).AddOfficer(TrafficOfficer, Zone);
                    break;
                case 10:
                    logout = true;
                    break;
            }
        } while (!logout);

    }

    @Override
    public String toString() {
        return "Admin name: " + this.Name;
    }

}
