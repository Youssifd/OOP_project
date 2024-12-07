package Traffic_Officer;
import Account.*;
import Admin.Admin;
import Vehicle.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TrafficOfficer extends Account implements display {
    private String assignedZone;
    private ArrayList<Traffic_Violation> violations;//list ->ArrayList

    public TrafficOfficer(String Id, String Name, String Email, String Password, String Contact, String assignedZone) {
        super(Id, Email, Password, Name);

        super.Contact = Contact;//temp
        if (assignedZone == null || assignedZone.trim().isEmpty()) {
            this.assignedZone = "General Zone";
        } else {
            this.assignedZone = assignedZone;
        }
        this.violations = new ArrayList<Traffic_Violation>();
    }

    public String getassignedZone() {
        return assignedZone;
    }

    public void setassignedZone(String assignedZone) {
        this.assignedZone = assignedZone;
    }

    public void recordvolation(Traffic_Violation violation) {
        if (violation == null) {
            System.out.println("Invalid violation! Cannot record.");
        }
        violations.add(violation);
        System.out.println("Violation recorded successfully by officer: " + Name);
    }

    @Override
    public void displayInfo() {
        System.out.println("================ Traffic Officer Info ================");
        System.out.println("Traffic Officer ID: " + getID());//getid -> getID
        System.out.println("Name: " + Name);//no getter for name
        System.out.println("Contact Info: " + Contact);//no getter for contact
        System.out.println("Assigned Zone: " + assignedZone);
        System.out.println("Violations Recorded: " + violations.size());
        System.out.println("=====================================================");
    }

    public void viewViolations() {
        System.out.println("Enter 1 if you want view violations \nEnter 2 if you want violation by specific type  ");
        Scanner input = new Scanner(System.in);
        char c;
        do {
            int choise = input.nextInt();
            do {
                if (choise == 1) {
                    if (violations.isEmpty()) {
                        System.out.println("No violations recorded yet.");
                    } else {
                        System.out.println("===== Violations Recorded by Officer " + Name + " =====");//no getter for name
                        for (Traffic_Violation violation : violations) {
                            System.out.println(violation);
                        }
                        System.out.println("==========================================================");
                    }

                } else if (choise == 2) {
                    System.out.println(" what is the type");
                    String type = input.next();
                    if (type == null || type.trim().isEmpty()) {
                        System.out.println("Invalid violation type! Please enter a valid type.");
                        return;
                    }
                    System.out.println("===== Searching for Violations of Type: " + type + " =====");
                    boolean found = false;

                    for (Traffic_Violation violation : violations) {
                        if (violation.getViolation_type().equalsIgnoreCase(type)) {
                            System.out.println(violation);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("No violations found for type: " + type);
                    }
                    System.out.println("==================================================");
                } else {
                    System.out.println("Invalid number ! Please enter a valid numer 1 or 2 .");
                }

            } while (choise == 1 || choise == 2);
            System.out.println("Do You Want opration again y|n");
            c = input.next().charAt(0);
        } while (c == 'y' || c == 'Y');

//    private int rewardPoints = 0;
//    public void updateRewardPoints() {
//        if (violations.size() % 10 == 0) {
//            rewardPoints += 50;
//            System.out.println("Congratulations " + Name + "! You Have earned 50 reward points. Total: " + rewardPoints);
//        }
//    }

    }
    public static String UniqueID(ArrayList <TrafficOfficer> accounts) {
        String id;
        System.out.print("Enter a unique ID: ");
        while (true) {
            id = Admin.cin.nextLine();
            String finalId = id;
            boolean isUnique = accounts.stream().noneMatch(admin -> admin.getID().equals(finalId));

            if (isUnique) {
                break;
            }
            System.out.println("This ID is already taken. Please enter a different ID: ");
        }
        return id;
    }

}

