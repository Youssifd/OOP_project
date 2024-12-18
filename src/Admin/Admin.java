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
        Scanner Cin;
        String Id, Name, Email, Password, Contact, assignedZone;
        do {
            Cin = new Scanner(System.in);
            Id = Exc.Unique("Enter your ID: ", Account.ids);

            Email = Display.validateEmail();
            out.print("Enter your Password: ");
            Password = Cin.nextLine();

            Name = Exc.Unique("Enter your Name: ",Account.Names);
            out.print("Enter your Contact_info: ");
            Contact = Cin.nextLine();
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
            c = Cin.next().charAt(0);
        } while (c == 'y' || c == 'Y');
        out.println("This offeceres are registered by Admin: " + this.Name);
        return arr;
    }

    public static void addZone(ArrayList<Zone> zones) {
        out.print("Enter the name of the zone: ");
        String name = cin.nextLine();
        out.print("Enter the Location of the zone: ");
        String Loc = cin.nextLine();
        Zone zone = new Zone(name, Loc);
        zones.add(zone);
        out.println("Zone ID: " + zone.getID() + ",added successfully!");
    }

    public ArrayList<Admin> addAdmins(ArrayList<Admin> admins, ArrayList<Owner> owners) {
        String id, name = "", email, password, contact;
        char hasAccount, continueAdding,c;
do {
    System.out.print("Does this Admin already have an account? (y/n): ");
    hasAccount = Admin.cin.next().charAt(0);
    Admin.cin.nextLine();

    if (hasAccount == 'y' || hasAccount == 'Y') {
        System.out.print("Enter their ID: ");
        id = Admin.cin.nextLine();

        for (Owner owner : owners) {
            if (owner.getID().equals(id)) {

                name = Exc.Unique("Enter their new username: ", Account.Names);

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
        do {
            id = Exc.Unique("Enter their Id: ", Account.ids);
            email = Display.validateEmail();
            System.out.print("Enter their Password: ");
            password = Admin.cin.nextLine();
            name = Exc.Unique("Enter their name: ", Account.Names);
            System.out.print("Enter their Contact_info: ");
            contact = Admin.cin.nextLine();

            admins.add(new Admin(id, name, email, password, contact));
            System.out.println("Account created successfully!");

            System.out.print("Do you want to create another account? (y/n): ");
            continueAdding = Admin.cin.next().charAt(0);
            Admin.cin.nextLine(); //  the leftover newline character.
        } while (continueAdding == 'y' || continueAdding == 'Y');
    }
    out.print("Do you want continue (y/n): ");
    c=Admin.cin.nextLine().charAt(0);
 //   Admin.cin.nextLine();
} while (c=='Y'||c=='y');
        return admins;
    }

   public void details()
   {
     Scanner c;
     char q;
     int i=0;
     out.println("you can show how many users include admins officers owners");
     out.print("1- Admins\n2- Officers\n3- Users\n4- Count for all\n");
       do {
           c = new Scanner(System.in);
     out.print("Enter your choice: ");
     i = Exc.infinite(i,4,1);
           switch (i) {
               case 1:
             out.println(Admin.Admincounter);
                break;
               case 2:
                   out.println(TrafficOfficer.officercount);
                   break;
               case 3:
                   out.println(Owner.ownerscount);
                   break;
               case 4:
                   out.println(Account.Acc_counter);

                   break;
           }
out.print("Do you want to continue (y/n): ");
           q=c.nextLine().charAt(0);
           if (q=='n'||q=='N')
               return;
       } while (true);
   }

    public static void AdminPage(ArrayList<Admin> admin, int index, ArrayList<Owner> owner, ArrayList<TrafficOfficer> TrafficOfficer, ArrayList<Zone> Zone, ArrayList<Traffic_Violation> traffic_Violation) {
        out.println("========================================================================");
        boolean logout = false;
        System.out.println("Welcome " + admin.get(index).Name + "!");
        do {
            System.out.println("1- View exist zones.\n2- Add traffic lights.\n3- Update traffic lights.\n4- Delete traffic lights.\n5- Add zone.\n6- View violations by vehicle or by zone.\n7- Generate traffic reports.\n8- Add new Admin.\n9- Change my password.\n10- Add new Traffic Officer.\n11- View your information.\n12- Details about the number of users.\n13- Logout.");
            out.println("......");
            out.print("Enter your choice: ");
            int a = 0, i = 0;

            a = Exc.infinite(0, 13, 1);
            if (a >= 1 && a <= 4) {
                for (int  j= 0 ; j < Zone.size(); j++) {

                    out.println(j+1 + "- " + Zone.get(j).getName());
                }
                out.print("Enter Number assigned zone: ");
                int choice = 0;
                choice = Exc.infinite(choice, Zone.size(), 1);
                i = choice - 1;

            }

            switch (a) {
                case 1:
                    Zone.get(i).getInfo();
                    break;
                case 2:
                    Zone.get(i).addTrafficLight();
                    break;
                case 3:
                    Zone.get(i).editTrafficLight();
                    break;
                case 4:
                    Zone.get(i).removeTrafficLight();
                    break;
                case 5:
                    addZone(Zone);
                    break;
                case 6://make global object of Traffic_Violation
                    Traffic_Violation.View_violations(traffic_Violation);// which violation-> Traffic_Violation.(traffic_Violation)static
                    break;
                case 7:
                  TrafficReport.generateReportBasedOnChoice(traffic_Violation,Zone);
                    break;
                case 8:
                    admin.get(index).addAdmins(admin, owner);
                    break;
                case 9:
                    admin.get(index).changePass();
                    break;
                case 10:
                    admin.get(index).AddOfficer(TrafficOfficer, Zone);
                    break;
                case 11:
                    admin.get(i).toString();
                    break;
                case 12:
               admin.get(i).details();
                    break;
                case 13:
                    logout = true;
                    break;
            }
            out.println("......................");
        } while (!logout);

    }

    @Override
    public String toString() {
        return "Admin name: " + Name+"\nAdmin id: " + getID()+"\nAdmin Email: "+ Email+"\nAdmin contact" + this.Contact;
    }

}