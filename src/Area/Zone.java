package Area;

import Admin.Exc;

import java.util.ArrayList;
import java.util.Scanner;

public class Zone {

    public ArrayList<Traffic_Lights> traffic_lights = new ArrayList<>();
    static int ZoneCounter = 0;
    public int numViolationOccured = 0;
    private final String ID;
    private final String Name;
    private final String Location;

    public Zone(String Name, String Location) {
        ZoneCounter++;
        if (ZoneCounter < 10) this.ID = "Z-00" + ZoneCounter;
        else if (ZoneCounter < 100) this.ID = "Z-0" + ZoneCounter;
        else this.ID = "Z-" + ZoneCounter;
        this.Name = Name;
        this.Location = Location;
    }

    public Zone(String ID, String Name, String Location, int x) {
        //for loading from file
        this.ID = ID;
        ID = ID.split("-")[1];
        if (Integer.parseInt(ID) > ZoneCounter)
            ZoneCounter = Integer.parseInt(ID);
        this.Name = Name;
        this.Location = Location;
        this.numViolationOccured = x;
    }

    public String getLocation() {
        return Location;
    }

    public String getName() {
        return Name;
    }

    public String getID() {
        return ID;
    }

    public void ViewZoneInfo() {
        Scanner cin = new Scanner(System.in);
        System.out.println("Zone ID: " + ID);
        System.out.println("Zone Name: " + Name);
        System.out.println("Zone Location: " + Location);
        char c;

        System.out.println("Do you want to view Traffic Lights? (y to view): ");
        c = cin.next().charAt(0);
        if (c == 'y' || c == 'Y') {
            for (Traffic_Lights traffic_light : traffic_lights) {
                traffic_light.Details();
            }
        }
    }

    public void addTrafficLight() {
        Traffic_Lights.addTrafficLight(traffic_lights, Location);
        ViewZoneInfo();
    }

    public void removeTrafficLight() {
        Scanner input = new Scanner(System.in);
        String Location; //replace with ID
        boolean flag = false;
        while (!flag) {
            System.out.print("Enter Location: ");
            Location = input.next();
            for (int i = 0; i < traffic_lights.size(); i++) {
                if (traffic_lights.get(i).getLocation().equals(Location)) {
                    flag = true;
                    traffic_lights.get(i).Stop();
                    traffic_lights.remove(i);
                    break;
                }
            }
            if (!flag) {
                System.out.println("Invalid Location");
            }
        }
    }

    public void editTrafficLight() {
        Scanner input = new Scanner(System.in);
        String Location; //replace with ID
        int s = 0;
        boolean flag = false;
        int index = 0;
        while (!flag) {
            System.out.print("Enter Location: ");
            Location = input.next();
            for (int i = 0; i < traffic_lights.size(); i++) {
                if (traffic_lights.get(i).getLocation().equals(Location)) {
                    // location not unique -> must be Zone location + special char (e.g. - )
                    // or use ID instead of location
                    flag = true;
                    index = i;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Invalid Location");
            }
        }
        System.out.println("1-Edit Time\n2-Edit Status\nEnter Choice: ");
        s = Exc.infinite(s, 2, 1);
        if (s == 1) {
            System.out.println("1-Red\n2-Yellow\n3-Green\nEnter Status: ");
            s = Exc.infinite(s, 3, 1);
            System.out.println("Enter Duration: ");
            int Duration = input.nextInt();
            switch (s) {
                case 3:
                    traffic_lights.get(index).EditTimeLights(Duration, "Green");
                    break;
                case 2:
                    traffic_lights.get(index).EditTimeLights(Duration, "Yellow");
                    break;
                case 1:
                    traffic_lights.get(index).EditTimeLights(Duration, "Red");
                    break;
            }
        } else if (s == 2) {
            System.out.println("Note: Duration will be the same as the previous one");
            System.out.println("1-Red\n2-Yellow\n3-Green\nEnter Status: ");
            s = Exc.infinite(s, 3, 1);
            switch (s) {
                case 1:
                    traffic_lights.get(index).ConfigurationByAdmin("Red");
                    break;
                case 2:
                    traffic_lights.get(index).ConfigurationByAdmin("Yellow");
                    break;
                case 3:
                    traffic_lights.get(index).ConfigurationByAdmin("Green");
                    break;
            }
        }

    }
}