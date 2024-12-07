package Vehicle;


import Admin.Admin;
import Admin.Exc;

import java.util.ArrayList;

public class Traffic_Violation {

    private String TracksViolationID;
    private final String Vehicle_ID;
    private String Violation_type;
    private String date;
    private double fine_amount;
    public String Status; // paid or not paid
    private final String whoIssued;//"Traffic Officer";

    public Traffic_Violation(String TracksViolationID, String Vehicle_ID,
                             String Violation_type, String date, double fine_amount, String whoIssued) {
        this.TracksViolationID = TracksViolationID;
        this.Vehicle_ID = Vehicle_ID;
        this.Violation_type = Violation_type;
        this.date = date;
        this.fine_amount = fine_amount;
        this.whoIssued = whoIssued;
        this.Status = "Not Paid";

    }

    public Traffic_Violation(String TracksViolationID, String Vehicle_ID,
                             String Violation_type, String date, double fine_amount, String whoIssued, String Status) {
        //for loading data
        this.TracksViolationID = TracksViolationID;
        this.Vehicle_ID = Vehicle_ID;
        this.Violation_type = Violation_type;
        this.date = date;
        this.fine_amount = fine_amount;
        this.whoIssued = whoIssued;
        this.Status = Status;

    }
    public String getViolationID() {
        return TracksViolationID;
    }

    public String getWhoIssued() {
        return whoIssued;
    }

    public void setTracksViolationID(String tracksViolationID) {
        TracksViolationID = tracksViolationID;
    }

    public String getVehicle_ID() {
        return Vehicle_ID;
    }


    public String getViolation_type() {
        return Violation_type;
    }

    public void setViolation_type(String violation_type) {
        Violation_type = violation_type;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getFine_amount() {
        return fine_amount;
    }

    public void setFine_amount(double fine_amount) {
        this.fine_amount = fine_amount;
    }

    public static void View_violations(ArrayList<Traffic_Violation> traffic_Violation, String by) {
        System.out.println("Do you want view violations by Zone or vechicle?\\n1-Zone\\n2-Vehicle\\nEnter Choice: ");
        int choice = 0;
        choice = Exc.infinite(choice, 2, 1);
        if (choice == 2) {
            System.out.println("Enter Vehicle ID: ");
            by = Admin.cin.next();
            for (int i = 0; i < traffic_Violation.size(); i++) {
                if (traffic_Violation.get(i).getVehicle_ID().equals(by))
                    System.out.println("Violation found:");
                System.out.println("TracksViolationID: " + traffic_Violation.get(i).getViolationID());
                System.out.println("Vehicle ID: " + traffic_Violation.get(i).getVehicle_ID());
                System.out.println("Violation Type: " + traffic_Violation.get(i).getViolation_type());
                System.out.println("Date: " + traffic_Violation.get(i).getDate());
                System.out.println("Fine Amount: " + traffic_Violation.get(i).getFine_amount());
                System.out.println("---------------------------------------");
                break;
            }

        } else {

            System.out.println("Enter Zone: ");
            by = Admin.cin.next();
            for (int i = 0; i < traffic_Violation.size(); i++) {
                if (traffic_Violation.get(i).getVehicle_ID().equals(by))
                    System.out.println("Violation found:");
                System.out.println("TracksViolationID: " + traffic_Violation.get(i).getViolationID());
                System.out.println("Vehicle ID: " + traffic_Violation.get(i).getVehicle_ID());
                System.out.println("Violation Type: " + traffic_Violation.get(i).getViolation_type());
                System.out.println("Date: " + traffic_Violation.get(i).getDate());
                System.out.println("Fine Amount: " + traffic_Violation.get(i).getFine_amount());
                System.out.println("---------------------------------------");
                // لحد ما ربنا يكرم بال zone
            }

        }
    }
}


