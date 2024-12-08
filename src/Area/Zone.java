package Area;
import  java.util.ArrayList;
public class Zone {

    public ArrayList <Traffic_Lights> traffic_light = new ArrayList<>();
    static  int ZoneCounter=0;
    private final String ID;
    private final String Name;
    private final String Location;

    public Zone( String Name, String Location) {
        ZoneCounter++;
        if(ZoneCounter<10) this.ID = "Z-00"+ZoneCounter;
        else if (ZoneCounter<100) this.ID = "Z-0"+ZoneCounter;
        else this.ID = "Z-"+ZoneCounter;
        this.Name = Name;
        this.Location = Location;
    }
    public Zone(String ID, String Name, String Location) {
        //for loading from file
        this.ID = ID;
        ID=ID.split("-")[1];
        if(Integer.parseInt(ID)>ZoneCounter)
            ZoneCounter=Integer.parseInt(ID);
        this.Name = Name;
        this.Location = Location;
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
    public void getinfo(){
        System.out.println("Zone ID: "+ID);
        System.out.println("Zone Name: "+Name);
        System.out.println("Zone Location: "+Location);
    }
    public void addTrafficLight(){
        Traffic_Lights.addTrafficLight(traffic_light,Location);
    }
    public void removeTrafficLight(){
        Traffic_Lights.delete(traffic_light);
    }
    public void editTrafficLight(){
        Traffic_Lights.update(traffic_light);
    }
}