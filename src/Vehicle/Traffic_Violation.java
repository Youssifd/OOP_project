package Vehicle;


import Admin.Admin;
import Admin.Exc;

import java.util.ArrayList;
import java.util.Scanner;

public class Traffic_Violation {
    public static int TV_counter = 0;
    public static ArrayList<Traffic_Violation> tra = new ArrayList<>();
    private final String TracksViolationID;
    private final String Vehicle_ID;
    private final String Violation_type;
    private final String date;
    private final double fine_amount;
    public String Status; // paid or not paid
    private final String whoIssued;//"Traffic Officer";
    private final String ZoneName;
    //boolean Seen = false;

    public Traffic_Violation(String Vehicle_ID,
                             String Violation_type, String date, String zoneName, double fine_amount, String whoIssued) {
        //for adding data
        TV_counter++;
        if (TV_counter < 10)
            this.TracksViolationID = "TV-0" + TV_counter;
        else
            this.TracksViolationID = "TV-" + TV_counter;
        //  this.TracksViolationID = TracksViolationID;
        this.Vehicle_ID = Vehicle_ID;//license plate
        this.Violation_type = Violation_type;
        this.date = date;
        this.fine_amount = fine_amount;
        this.whoIssued = whoIssued;
        this.ZoneName = zoneName;
        this.Status = "Not Paid";

    }

    public Traffic_Violation(String TracksViolationID, String Vehicle_ID,
                             String Violation_type, String date, String zoneName, double fine_amount, String whoIssued, String Status) {
        //for loading data

        this.TracksViolationID = TracksViolationID;
        TracksViolationID = TracksViolationID.split("-")[1];
        if (Integer.parseInt(TracksViolationID) > TV_counter)
            TV_counter = Integer.parseInt(TracksViolationID);
        this.Vehicle_ID = Vehicle_ID;
        this.Violation_type = Violation_type;
        this.date = date;
        this.fine_amount = fine_amount;
        this.ZoneName = zoneName;
        this.whoIssued = whoIssued;
        this.Status = Status;

    }

    public String getViolationID() {
        return TracksViolationID;
    }

    public String getZoneName() {
        return ZoneName;
    }

    public String getWhoIssued() {
        return whoIssued;
    }

    public String getVehicle_ID() {
        return Vehicle_ID;
    }

    public String getViolation_type() {
        return Violation_type;
    }

    public String getDate() {
        return date;
    }

    public double getFine_amount() {
        return fine_amount;
    }

    public static void View_Details(ArrayList<Traffic_Violation> TV) {
        //for officer
        for (int i = 0; i < TV.size(); i++) {
            System.out.println("#" + (i + 1) + ":");
            System.out.println("Violation ID: " + TV.get(i).getViolationID());
            System.out.println("Violation Type: " + TV.get(i).getViolation_type());
            System.out.println("Date: " + TV.get(i).getDate());
            System.out.println("Zone: " + TV.get(i).getZoneName());
            System.out.println("Fine Amount: " + TV.get(i).getFine_amount());
            System.out.println("Issued by: " + TV.get(i).getWhoIssued());
            System.out.println("Status: " + TV.get(i).Status);
            System.out.println("_________________________");
        }
    }

    public static void View_violations(ArrayList<Traffic_Violation> traffic_Violation) {
        Scanner scan;
        char c;
        String by;
        System.out.println("Do you want view violations by Zone or vehicle?\n1-Zone\n2-Vehicle");
        do {
            System.out.print("Enter your choice: ");
            scan = new Scanner(System.in);
            int choice = 0;
            boolean found = false;

            choice = Exc.infinite(choice, 2, 1);
            if (choice == 2) {
                System.out.println("Enter license Plate: ");
                by = Admin.cin.next();

                for (Traffic_Violation trafficViolation : traffic_Violation) {
                    if (trafficViolation.getVehicle_ID().equals(by)) {
                        System.out.println("TracksViolationID: " + trafficViolation.getViolationID());
                        System.out.println("Vehicle ID: " + trafficViolation.getVehicle_ID());
                        System.out.println("Violation Type: " + trafficViolation.getViolation_type());
                        System.out.println("Date: " + trafficViolation.getDate());
                        System.out.println("Fine Amount: " + trafficViolation.getFine_amount());
                        System.out.println("---------------------------------------");
                        found = true;

                    }
                }
                if (!found) {
                    System.out.println("No Violations Found");
                }

            } else {

                System.out.print("Enter Zone name: ");
                by = Admin.cin.next();
                for (Traffic_Violation trafficViolation : traffic_Violation) {
                    if (trafficViolation.getZoneName().equals(by)) {
                        System.out.println("TracksViolationID: " + trafficViolation.getViolationID());
                        System.out.println("Vehicle ID: " + trafficViolation.getVehicle_ID());
                        System.out.println("Violation Type: " + trafficViolation.getViolation_type());
                        System.out.println("Date: " + trafficViolation.getDate());
                        System.out.println("Fine Amount: " + trafficViolation.getFine_amount());
                        System.out.println("---------------------------------------");
                        found = true;
                    }

                }
                if (!found) {
                    System.out.println("No Violations Found");
                }

            }
            System.out.print("Do you want continue(y/n): ");
            c = scan.nextLine().charAt(0);
        } while (c == 'y' || c == 'Y');
    }
}


