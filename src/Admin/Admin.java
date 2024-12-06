package Admin;
import Account.*;
import Area.Zone;

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;
public class Admin extends Account {
    public int counter =0;
    public static Scanner cin=new Scanner(System.in);
    public Admin(String id,String Name,String email,String Passowrd) {
        super(id, email, Passowrd, Name);
    }
    public boolean LoginAdmin() {

        do {
            String name;
            String pass = "";
            out.print("\"Enter username: \"");
            name = cin.nextLine();
            out.print("\"Enter Password: \"");
            pass = cin.nextLine();
            for (int i = 0; i < 6; i++) {
                if (name.equals("Marwan") && pass.equals("MS"))
                    return true;
            }
            counter++;
            out.println("invalid username or password");
        } while(counter <5);
      //  x=false;
        return false;
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



}
