package Traffic_Officer;

import Account.*;
import Admin.*;
import Area.Zone;
import Vehicle.*;

import Account.Notification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

public class TrafficOfficer extends Account  {
    private final String assignedZone;
    public ArrayList<Traffic_Violation> violations;//list ->ArrayList
    public static int officercount = 0;

    public TrafficOfficer(String Id, String Name, String Email, String Password, String Contact, String assignedZone) {
        super(Id, Email, Password, Name);

        this.Contact = Contact;
        if (assignedZone == null || assignedZone.trim().isEmpty()) {
            this.assignedZone = "General Zone";
        } else {
            this.assignedZone = assignedZone;
        }
        this.violations = new ArrayList<Traffic_Violation>();
        officercount++;
    }

    public void addviolations(Traffic_Violation violation) {
        violations.add(violation);
    }

    public String getassignedZone() {
        return assignedZone;
    }

    public void recordvolation(Traffic_Violation TV, ArrayList<Owner> o, int OwnerIndex, int VehicleIndex) {

        violations.add(TV);//for add violation to officer
        System.out.println("Violation recorded successfully by officer: " + Name);
        o.get(OwnerIndex).vehicle.get(VehicleIndex).addViolation(TV);//for add violation to owner
        System.out.println("Violation added to Vehicle with License Plate: " + o.get(OwnerIndex).vehicle.get(VehicleIndex).getLicensePlate());
        // String Message = Notification.StandardMessage(o.get(OwnerIndex).Name, TV);
//        Notification.sendNotification(o.get(OwnerIndex).notifications, Message);
        o.get(OwnerIndex).sendNotification(TV);

    }

    public void viewViolations(int ch) {
        Scanner input = new Scanner(System.in);
        /*  System.out.println("Enter 1 if you want view violations \nEnter 2 if you want violation by specific type  ");
        Scanner input = new Scanner(System.in);*/

       /* do {
//            int choise =input.nextInt();
            do {*/
        if (ch == 1) {
            if (violations.isEmpty()) {
                System.out.println("No violations recorded yet.");
            } else {
                System.out.println("===== Violations Recorded by Officer " + Name + " =====");//no getter for name
//                            System.out.println(violation); view address
                for (Traffic_Violation tv : violations) {
                    System.out.println("Violation found:");
                    System.out.println("TracksViolationID: " + tv.getViolationID());
                    System.out.println("Vehicle ID: " + tv.getVehicle_ID());
                    System.out.println("Violation Type: " + tv.getViolation_type());
                    System.out.println("Date: " + tv.getDate());
                    System.out.println("Fine Amount: " + tv.getFine_amount());
                    System.out.println("---------------------------------------");
                }
                System.out.println("==========================================================");
            }

        } else if (ch == 2) {
            System.out.println(" what is the type");
            String type = input.next();
            if (type == null || type.trim().isEmpty()) {
                System.out.println("Invalid violation type! Please enter a valid type.");
                return;
            }
            System.out.println("===== Searching for Violations of Type: " + type + " =====");
            boolean found = false;

            for (Traffic_Violation tv : violations) {
                if (tv.getViolation_type().equalsIgnoreCase(type)) {
//                            System.out.println(violation); //view address
                    System.out.println("Violation found:");
                    System.out.println("TracksViolationID: " + tv.getViolationID());
                    System.out.println("Vehicle ID: " + tv.getVehicle_ID());
                    System.out.println("Violation Type: " + tv.getViolation_type());
                    System.out.println("Date: " + tv.getDate());
                    System.out.println("Fine Amount: " + tv.getFine_amount());
                    System.out.println("---------------------------------------");
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No violations found for type: " + type);
            }
            System.out.println("==================================================");

        }
                /*else {
                    System.out.println("Invalid number ! Please enter a valid numer 1 or 2 .");
                }
           } while (choise == 1 || choise == 2);
            System.out.println("Do You Want opration again y|n");
            c = input.next().charAt(0);
        } while (c == 'y' || c == 'Y');*/

//    private int rewardPoints = 0;
//    public void updateRewardPoints() {
//        if (violations.size() % 10 == 0) {
//            rewardPoints += 50;
//            System.out.println("Congratulations " + Name + "! You Have earned 50 reward points. Total: " + rewardPoints);
//        }
//    }

    }


    public static void OfficerPage(ArrayList<TrafficOfficer> officer, int index, ArrayList<Owner> owners, ArrayList<Zone> z) {
        Scanner input = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM-HH:mm");
        boolean logout = false;

        /*String Vehicle_ID,String Violation_type, String date, double fine_amount, String whoIssued*/
        out.println("Welcome " + officer.get(index).Name + " :)");
        do {
            out.println("1- View the violations you added\n2- Search Violations added you\n3- Record Violation\n4- Change Password\n5- Logout");
            out.print("Enter your choice: ");
            int choise = 0;
            choise = Exc.infinite(choise, 5, 1);
            switch (choise) {
                case 1:
                    officer.get(index).viewViolations(1);
                    break;
                case 2:
                    officer.get(index).viewViolations(2);
                    break;
                case 3:
                    String Vehicle_Licence = " ", Violation_type = " ", date, whoIssued, zoneName;
                    double fine_amount = 0;
                    int ownerIndex = -1, vehicleIndex = -1, tryCounter = 1;
                    char c;
                    boolean exit = false;
                    System.out.println("Enter Vehicle ID: ");
                    do {
                        tryCounter++;

                        Vehicle_Licence = input.next();
                        if (tryCounter > 3) {
                            out.println("do you want to try again? y/n");
                            c = input.next().charAt(0);
                            if (c == 'n' || c == 'N') {
                                exit = true;
                                break;
                            }

                        }
                    } while (!Vehicle.CheckExist(Vehicle_Licence));
                    if (exit) {
                        break;
                    }
                    String LicensePlate = Vehicle_Licence;
                    String getVtype = " ";
                    for (int i = 0; i < owners.size(); i++) {
                        for (int j = 0; j < owners.get(i).vehicle.size(); j++) {
                            if (owners.get(i).vehicle.get(j).getLicensePlate().equals(LicensePlate)) {
                                getVtype = owners.get(i).vehicle.get(j).getType();
                                ownerIndex = i;
                                vehicleIndex = j;
                            }
                        }
                    }
                    int choice = 0;
                    if (getVtype.equals("Bike")) {
                        System.out.println("Choose Violation Type\n1- Speeding\n2- Parking\n3- Running Red Light\n4- No License Plate\n5- No Registration\n6- No Helmet");
                        System.out.println("Enter your choice: ");
                        choice = Exc.infinite(choice, 6, 1);

                    } else {
                        System.out.println("Choose Violation Type\n1- Speeding\n2- Parking\n3- Running Red Light\n4- No License Plate\n5- No Registration\n");
                        System.out.println("Enter your choice: ");
                        choice = Exc.infinite(choice, 5, 1);

                    }
                    switch (choice) {
                        case 1:
                            Violation_type = "Speeding";
                            fine_amount = 100;
                            if (getVtype.equals("Bike"))
                                fine_amount *= 2;
                            else if (getVtype.equals("Car"))
                                fine_amount *= 3;
                            else
                                fine_amount *= 4;
                            break;
                        case 2:
                            Violation_type = "Parking";
                            fine_amount = 50;
                            if (getVtype.equals("Bike"))
                                fine_amount *= 2;
                            else if (getVtype.equals("Car"))
                                fine_amount *= 3;
                            else
                                fine_amount *= 4;
                            break;
                        case 3:
                            Violation_type = "Running Red Light";
                            fine_amount = 150;
                            if (getVtype.equals("Bike"))
                                fine_amount *= 2;
                            else if (getVtype.equals("Car"))
                                fine_amount *= 3;
                            else
                                fine_amount *= 4;
                            break;
                        case 4:
                            Violation_type = "No License Plate";
                            fine_amount = 1500;
                            if (getVtype.equals("Bike"))
                                fine_amount *= 2;
                            else if (getVtype.equals("Car"))
                                fine_amount *= 3;
                            else //truck
                                fine_amount *= 4;
                            break;
                        case 5:
                            Violation_type = "No Registration";
                            fine_amount = 5000;
                            if (getVtype.equals("Bike"))
                                fine_amount *= 2;
                            else if (getVtype.equals("Car"))
                                fine_amount *= 3;
                            else
                                fine_amount *= 4;
                            break;
                        case 6:
                            Violation_type = "No Helmet";
                            fine_amount = 200;
                            break;

                    }
                    date = formatter.format(new Date());
                    whoIssued = officer.get(index).Name;
                    zoneName = officer.get(index).assignedZone; // assignedZone not taken from user
                    for (Zone zone : z) {
                        if (zone.getName().equals(zoneName)) {
                            zone.numViolationOccured++;
                        }
                    }
                    Traffic_Violation violation = new Traffic_Violation(LicensePlate, Violation_type, date, zoneName, fine_amount, whoIssued);
                    officer.get(index).recordvolation(violation, owners, ownerIndex, vehicleIndex);
                    Traffic_Violation.tra.add(violation);
                    break;
                case 4:
                    officer.get(index).changePass();
                    break;
                case 5:
                    logout = true;
                    break;
            }
        } while (!logout);
    }
}

