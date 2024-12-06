package Area;
import  java.util.ArrayList;
public class Zone {

    ArrayList<Traffic_Lights> traffic_light = new ArrayList<>();
    private final String ID;
    private final String Name;
    private final String Location;

    public Zone(String ID, String Name, String Location) {
        this.ID = ID;
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
    public void addTrafficLight(){
        Traffic_Lights.addTrafficLight(traffic_light);
    }
    public void removeTrafficLight(){
        Traffic_Lights.delete(traffic_light);
    }
    public void editTrafficLight(){
        Traffic_Lights.update(traffic_light);
    }
}