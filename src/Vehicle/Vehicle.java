package Vehicle;

import Admin.Exc;

import java.util.ArrayList;
import java.util.Scanner;

public class Vehicle {
    private final String id;
    private final String type;
    private final String licensePlate;
    private final String owner;
    public ArrayList<Traffic_Violation> TV = new ArrayList<>();
    public static int counter = 0;
    static ArrayList<String> licensePlates = new ArrayList<>();

    // Constructor
    public Vehicle(String type, String licensePlate, String owner) {
        counter++;
        if (counter < 10) {
            this.id = "VH-0" + counter;
        } else {
            this.id = "VH-" + counter;
        }
        this.type = type;
        this.licensePlate = licensePlate;
        licensePlates.add(licensePlate);
        this.owner = owner;
    }


    public Vehicle(String id, String type, String licensePlate, String owner) {
        //for loading data

        this.id = id;
        id = id.split("-")[1];
        if (Integer.parseInt(id) > counter)
            counter = Integer.parseInt(id);
        this.type = type;
        this.licensePlate = licensePlate;
        licensePlates.add(licensePlate);
        this.owner = owner;
    }


    // Getters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwner() {
        return owner;
    }

    public static boolean CheckExist(String licensePlate) {
        if (licensePlates.contains(licensePlate)) {
            return true;
        } else {
            System.out.println("This license plate is not exist");
            System.out.println("Please enter a valid one.");
            return false;
        }
    }

    public void getData() {
        System.out.println("Vehicle ID: " + id);
        System.out.println("Type: " + type);
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Owner: " + owner);
    }

    //    update by yousefelsayed
    public void addViolation(Traffic_Violation violation) {
        TV.add(violation);
    }

    //       *******
    public static void addVehicles(ArrayList<Vehicle> vehicles,String OwnerName) {
        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;

        while (continueInput) {
            System.out.println("1- Bike\n2- Car\n3- Truck");
            System.out.print("Enter vehicle type: ");
            int choice = 0;
            choice = Exc.infinite(choice, 4, 1);
           String type="";
            switch (choice){
                case 1 -> type = "Bike";
                case 2 -> type = "Car";
                case 3 -> type = "Truck";
            }

            String licensePlate;
            while (true) {
                System.out.println("Enter license plate:");
                licensePlate = scanner.nextLine();

                if(licensePlate.isEmpty()){
                    continue;
                } else
                if (licensePlates.contains(licensePlate)) {
                    System.out.println("This license plate is already used. Please enter a unique one.");
                } else {
                    break;
                }
            }

           /* System.out.println("Enter owner name:");
            String owner = scanner.nextLine();*/
            Vehicle vehicle = new Vehicle(type, licensePlate, OwnerName);
            vehicles.add(vehicle);
            System.out.println("Vehicle added: " + vehicle.licensePlate);

            System.out.println("Do you want to add another vehicle? (yes/no):");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                continueInput = false;
            }
        }
    }

    public   void PayFine (){

        //for owner
        Scanner in = new Scanner(System.in);
        if(TV.isEmpty()){
            System.out.println("This vehicle has no fines.");
            return;
        }
        //Show fines term
        System.out.println("Violations found:");
        for (int i = 0; i < TV.size(); i++) {
            System.out.println("#" + (i + 1) + ":");
            System.out.println("Violation ID: " + TV.get(i).getViolationID());
            System.out.println("Violation Type: " + TV.get(i).getViolation_type());
            System.out.println("Date: " + TV.get(i).getDate());
            System.out.println("Zone: " + TV.get(i).getZoneName());
            System.out.println("Fine Amount: " + TV.get(i).getFine_amount());
            System.out.println("Issued by: " + TV.get(i).getWhoIssued());
            System.out.println("Status: " + TV .get(i).Status);
            System.out.println("_________________________");
        }
            char ch;
        do {
            System.out.println("Enter the number of the violation you want to pay the fine for(0 for exit):");
            //check invalid term
            int violationIndex = 0;
            violationIndex = Exc.infinite(1, TV.size(), 0);
            if (violationIndex == 0) return;
            //Pay term
            if (TV.get(violationIndex - 1).Status.equals("Not Paid")) {
                TV.get(violationIndex - 1).Status = "Paid";
                System.out.println("Fine paid successfully. :)");
            } else {
                System.out.println("This fine has already been paid.");
            }
            //check continue term
            System.out.println("Do you want to pay another fine? (Y/N)");
            ch = in.next().charAt(0);
            while (ch != 'Y' && ch != 'y' && ch != 'N' && ch != 'n') {
                System.out.println("Invalid input. Please enter Y or N.");
                ch = in.next().charAt(0);
            }

        }while (ch != 'Y' && ch != 'y');
    }
}


