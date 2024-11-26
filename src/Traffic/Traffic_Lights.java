package Traffic;


import java.util.Scanner;

public class Traffic_Lights implements Runnable {
    /*
     * Has an ID, Location, Status (Red/Green/Yellow), and Duration.
     * Operates on a cycle or as configured by an admin.
     */
    public static Scanner input = new Scanner(System.in);
    public static int Traffic_counter = 0;
    int redTime = 25;
    int greenTime = 20;
    int yellowTime = 5;
    private String ID;
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

    public int getDuration() {
        return Duration;
    }
}
