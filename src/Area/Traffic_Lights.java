package Area;


import Admin.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Traffic_Lights implements Runnable {

    public static Scanner input = new Scanner(System.in);
    public static int Traffic_counter = 0;
    public int redTime = 25;
    public int greenTime = 20;
    public int yellowTime = 5;
    private final String ID;
    private final String Location;
    public String Status;//Red|Green|Yellow
    private int Duration;
    private Boolean isRunning = true;   //to stop Traffic light
    public Thread thread;

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

    public Traffic_Lights(String ID, String Location, int Duration, String Status, int[] arr) {
        this.ID = ID;
        ID = ID.split("-")[1];
        this.Location = Location;
        this.Duration = Duration;
        this.Status = Status;
        this.redTime = arr[0];
        this.yellowTime = arr[1];
        this.greenTime = arr[2];
        if (Integer.parseInt(ID) > Traffic_counter)
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


    public void ConfigurationByAdmin(String Status) {
        switch (Status) {
            case "Red" -> Duration = redTime;
            case "Green" -> Duration = greenTime;
            case "Yellow" -> Duration = yellowTime;
            default -> {
                System.out.println("Invalid Status");
                return;
            }

        }
        this.Status = Status;


    }

    public void EditTimeLights(int Duration, String Status) {
        Duration = checkDuration(Duration);
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

    public static void addTrafficLight(ArrayList<Traffic_Lights> Traffic_Lights, String location) {
        
        int temp = Traffic_Lights.size() + 1;
        String Location = location + "-" + temp;
        String Status = "";
        System.out.println("Enter temp Duration for first time(greater than 1): ");
        int Duration = input.nextInt();

        Duration = checkDuration(Duration);

        System.out.println("1-Red\n2-Yellow\n3-Green\nEnter Status: ");
        int choice = 0; //check valid from admin class
        choice = Exc.infinite(0, 3, 1);
        if (choice == 1) {
            Status = "Red";
        } else if (choice == 2) {
            Status = "Yellow";
        } else if (choice == 3) {
            Status = "Green";
        }
        Traffic_Lights.add(new Traffic_Lights(Location, Duration, Status));
        Traffic_Lights.get(Traffic_Lights.size() - 1).Start();
        System.out.println("Traffic Light added successfully!");

    }

}

