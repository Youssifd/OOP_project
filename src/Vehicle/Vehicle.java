package Vehicle;

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

    public static boolean ChechExist(String licensePlate) {
        if (licensePlates.contains(licensePlate)) {
            return true;
        } else {
            System.out.println("This license plate is not exist");
            System.out.println("Please enter a valid one.");
            return false;
        }
    }

    //    update by yousefelsayed
    public void addViolation(Traffic_Violation violation) {
        TV.add(violation);
    }

    //       *******
    public static void addVehicles(ArrayList<Vehicle> vehicles) {
        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;

        while (continueInput) {
            System.out.println("Enter vehicle type (Car, Truck, Bike):");
            String type = scanner.nextLine();

            String licensePlate;
            while (true) {
                System.out.println("Enter license plate:");
                licensePlate = scanner.nextLine();

                if (licensePlates.contains("," + licensePlate + ",")) {
                    System.out.println("This license plate is already used. Please enter a unique one.");
                } else {
                    break;
                }
            }

            System.out.println("Enter owner name:");
            String owner = scanner.nextLine();
            Vehicle vehicle = new Vehicle(type, licensePlate, owner);
            vehicles.add(vehicle);
            System.out.println("Vehicle added: " + vehicle);

            System.out.println("Do you want to add another vehicle? (yes/no):");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                continueInput = false;
            }
        }
    }
}


