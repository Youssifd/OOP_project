package Area;

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

    public Zone(String ID, String Name, String Location,int x) {
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

    public void getInfo() {
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
        getInfo();
    }

    public void removeTrafficLight() {
        Traffic_Lights.delete(traffic_lights);
    }

    public void editTrafficLight() {
        Traffic_Lights.update(traffic_lights);
    }
}