package Area;


import Admin.*;
        import java.util.ArrayList;
import java.util.Scanner;

public class Traffic_Lights implements Runnable {
    /*
     * Has an ID, Location, Status (Red/Green/Yellow), and Duration.
     * Operates on a cycle or as configured by an admin.
     */
    public static Scanner input = new Scanner(System.in);
    public static int Traffic_counter = 0;
    int  redTime = 25;
    int greenTime = 20;
    int yellowTime = 5;
    private final String ID;
    private String Location;
    public String Status;//Red/Green/Yellow
    private int Duration;
    private Boolean isRunning = true;   //to stop Traffic light
    public Thread thread; // for starting and stopping the thread of the traffic light

    public Traffic_Lights(String Location, int Duration, String Status) {
        Traffic_counter++;
        if (Traffic_counter < 9) {
            this.ID = "TL-0" + Traffic_counter;
        } else {
            this.ID = "TL-" + Traffic_counter;
        }
        this.Location = Location;
        this.Duration = Duration;
        this.Status = Status;
    }

    public Traffic_Lights(String ID, String Location, int Duration, String Status) {
        this.ID = ID;
        ID = ID.split("-")[1];
        this.Location = Location;
        this.Duration = Duration;
        this.Status = Status;
        Traffic_counter = Integer.parseInt(ID);
    }

    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(Duration * 1000L);
                Auto_Change_Status();
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    public void Start() {
        thread = new Thread(this);
        thread.start();
    }

    public void Stop() {
        isRunning = false;
        thread.interrupt(); //force stop the thread

    }

    private void Auto_Change_Status() {
        //Operates on a cycle automatically
        switch (Status) {
            case "Red":
                Status = "Green";
                Duration = greenTime;
                break;
            case "Green":
                Status = "Yellow";
                Duration = yellowTime;
                break;
            case "Yellow":
                Status = "Red";
                Duration = redTime;
                break;
        }
    }


    public void ConfigurationByAdmin(int Duration, String Status) {
        if (Duration < 1) {
            System.out.println("Invalid Duration :(");
            System.out.println("Enter Duration: ");
            Duration = input.nextInt();
            ConfigurationByAdmin(Duration, Status); //Recursion to enter a valid Duration
            return;
        }
        this.Duration = Duration;
        this.Status = Status;
    }

    public void EditTimeLights(int Duration, String Status) {
        switch (Status) {
            case "Red":
                redTime = Duration;
                break;
            case "Green":
                greenTime = Duration;
                break;
            case "Yellow":
                yellowTime = Duration;
                break;
        }
    }

    public String getID() {
        return ID;
    }

    public String getLocation() {
        return Location;
    }

    public int getDuration() {
        return Duration;
    }

    public void Details() {
        System.out.println("ID: " + ID);
        System.out.println("Location: " + Location);
        System.out.println("Status: " + Status);
        System.out.println("Duration: " + Duration);
    }

    public static int checkDuration(int Duration) {
        if (Duration <= 1) {
            System.out.println("Invalid Duration :(");
            System.out.println("Enter Duration: ");
            Duration = input.nextInt();
            checkDuration(Duration); //Recursion to enter a valid Duration

        }
        return Duration;
    }

    public static void addTrafficLight(ArrayList<Traffic_Lights> Traffic_Lights) {
        System.out.println("Enter Location: ");
        String Location = input.next();
        String Status = "";
        System.out.println("Enter Duration(greater than 1): ");
        int Duration = input.nextInt();

        Duration = Area.Traffic_Lights.checkDuration(Duration);

        System.out.println("1-Red\n2-Yellow\n3-Green\nEnter Status: ");
        int choice = input.nextInt(); //check valid from admin class

        if (choice == 1) {
            Status = "Red";
        } else if (choice == 2) {
            Status = "Yellow";
        } else if (choice == 3) {
            Status = "Green";
        }
        Traffic_Lights.add(new Traffic_Lights(Location, Duration, Status));

    }
    public static  void update(ArrayList<Traffic_Lights> trafficLightsList) {
        String Location; //replace with ID
        int s=0;
        boolean flag = false;
        int index=0;
        while (!flag) {
            System.out.print("Enter Location: ");
            Location = input.next();
            for (int i = 0; i < trafficLightsList.size(); i++) {
                if (trafficLightsList.get(i).getLocation().equals(Location)) {
                    // location not unique -> must be Zone location + special char (e.g. '-')
                    // or use ID instead of location
                    flag = true;
                    index=i;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Invalid Location");
            }
        }
        System.out.println("1-Edit Time\n2-Edit Status\nEnter Choice: ");
        s=Exc.infinite(s,2,1);
        if(s==1)
        {
            System.out.println("1-Red\n2-Yellow\n3-Green\nEnter Status: ");
            s=Exc.infinite(s,3,1);
            System.out.println("Enter Duration: ");
            int Duration = input.nextInt();
            Duration = Traffic_Lights.checkDuration(Duration);
            switch (s) {
                case 3:
                    trafficLightsList.get(index).EditTimeLights(Duration,"Green");
                    break;
                case 2:
                    trafficLightsList.get(index).EditTimeLights(Duration,"Yellow");
                    break;
                case 1:
                    trafficLightsList.get(index).EditTimeLights(Duration,"Red");
                    break;
            }
        }
        else
        if (flag) {
            System.out.println("1-Red\n2-Yellow\n3-Green\nEnter Status: ");
            s=Exc.infinite(s,3,1);
            switch (s) {
                case 3:
                    trafficLightsList.get(index).ConfigurationByAdmin(trafficLightsList.get(index).greenTime,"Green");
                    break;
                case 2:
                    trafficLightsList.get(index).ConfigurationByAdmin(trafficLightsList.get(index).yellowTime,"Yellow");
                    break;
                case 1:
                    trafficLightsList.get(index).ConfigurationByAdmin(trafficLightsList.get(index).redTime,"Red");
                    break;
            }
        }

    }
    public static void delete(ArrayList<Traffic_Lights> trafficLightsList)
    {
        String Location; //replace with ID
        boolean flag = false;
        while (!flag) {
            System.out.print("Enter Location: ");
            Location = input.next();
            for (int i = 0; i < trafficLightsList.size(); i++) {
                if (trafficLightsList.get(i).getLocation().equals(Location)) {
                    flag = true;
                    trafficLightsList.remove(i);
                    break;
                }
            }
            if (!flag) {
                System.out.println("Invalid Location");
            }
        }
    }

}

