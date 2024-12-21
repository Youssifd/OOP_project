package Admin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Area.Zone;
import Vehicle.Traffic_Violation;

public class TrafficReport {
    public static int[] arr = new int[6];

    public static void generateReportBasedOnChoice(ArrayList<Traffic_Violation> trafficViolations, ArrayList<Zone> zones) {
        char c ;
        do {
            for (int i=0;i<arr.length;i++)
                arr[i]=0;
            z(trafficViolations);
            System.out.println("1. High-Density Zones Report based on Time");
            System.out.println("2. Most Violated Zone");
            System.out.println("3. Most Frequent Violation Type");
            System.out.print("Choose the type of report you want: ");
            int choice = 0;
            choice = Exc.infinite(choice, 3, 1);
            switch (choice) {
                case 1:
                    generateHighDensityZonesReportBasedOnTime();
                    break;
                case 2:
                    reportbymostviolation(zones);
                    break;
                case 3:
                    generateM(trafficViolations);
                    break;

            }
            System.out.print("Do you want to continue generate report (y/n): ");
            c = Admin.cin.next().charAt(0);
        } while (c == 'y' || c == 'Y');
    }

    public static void generateHighDensityZonesReportBasedOnTime() {
        String inputTime;
        Scanner cin;
        char c;
        do {
            LocalTime userTime = null;
            boolean validTime = false;
            cin = new Scanner(System.in);
            while (!validTime) {
                System.out.print("Enter the time (HH:mm:ss): ");
                inputTime = cin.nextLine();
                if (inputTime.matches("^([01]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])$")) {
                    try {
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        userTime = LocalTime.parse(inputTime, timeFormatter);
                        validTime = true;
                    } catch (Exception e) {
                        System.out.println("Invalid time format. Please enter the time in HH:mm:ss format.");
                    }
                } else {
                    System.out.println("Invalid time format. Please enter the time in HH:mm:ss format.");
                }
            }
            String reportTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String congestionCause = getCongestionCause(userTime);
            System.out.println("Time Period: " + userTime + " | " + congestionCause);
            System.out.println(" Zones Report (Generated at: " + reportTime + "):");
            System.out.print("Do you want to generate based on another time (y/n): ");
            c = cin.nextLine().charAt(0);
        } while (c == 'y' || c == 'Y');
    }


    private static String getCongestionCause(LocalTime time) {
        if (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(17, 0))) {
            return "There is traffic congestion during this period due to employees leaving work and students leaving schools and universities";
        } else if (time.isAfter(LocalTime.of(7, 0)) && time.isBefore(LocalTime.of(9, 0))) {
            return "There is traffic congestion during this period due to employees heading to work and students heading to schools and universities";
        } else {
            return "There is no noticeable traffic congestion during this period";
        }
    }


    public static void reportbymostviolation(ArrayList<Zone> z) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String reportTime = LocalTime.now().format(timeFormatter);
        System.out.println("\nMost Violated Zone Report:");
        for (int i = 0; i < z.size(); i++)
            if (z.get(i).numViolationOccured > 0) {
                System.out.println(z.get(i).getName() + " | Violations: " + z.get(i).numViolationOccured);
            }
        System.out.println("\nFrequent Violations Report (Generated at: " + reportTime + "):");
    }


    public static void generateM(ArrayList<Traffic_Violation> trafficViolations) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String reportTime = LocalTime.now().format(timeFormatter);

        System.out.println("\nFrequent Violations Report: " + trafficViolations.size());
        System.out.println("Speeding: " + arr[0]);
        System.out.println("Parking: " + arr[1]);
        System.out.println("Running Red Light: " + arr[2]);
        System.out.println("No License Plate: " + arr[3]);
        System.out.println("No Registration: " + arr[4]);
        System.out.println("No Helmet: " + arr[5]);
        System.out.println("Frequent Violations Report (Generated at: " + reportTime + "):\n");
    }

    public static void avg(ArrayList<Traffic_Violation> trafficViolations) {
        Scanner input;
        Integer x ;
     System.out.println("Note that this is not the exact price; it is an approximate range.");
        char p;
        int a = 0;
        System.out.println("1- for all\n2- for each type\n3- specific type");
        do {
            x=0;
            for (int i=0;i<arr.length;i++)
                arr[i]=0;
        z(trafficViolations);
        x += arr[0] * 3 * 100;
        x += arr[1] * 3 * 50;
        x += arr[2] * 3 * 150;
        x += arr[3] * 3 * 1500;
        x += arr[4] * 3 * 5000;
        x += arr[5] * 200;
            input = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            a = Exc.infinite(a, 3, 1);
            switch (a) {
                case 1:
                    if (trafficViolations.size() > 0) {
                        System.out.println("Frequent Violations Report: " + trafficViolations.size());
                        System.out.println("Average of total violations =  " + x);
                    } else {
                        System.out.println("No violations to calculate an average.");
                    }
                    break;
                case 2:
                    System.out.println("Average of each type:");
                    if (arr[0] > 0) {
                        System.out.println("1- Speeding: " + arr[0] * 3 * 100);
                    } else {
                        System.out.println("1- Speeding: No violations");
                    }
                    if (arr[1] > 0) {
                        System.out.println("2- Parking: " + arr[1] * 3 * 50);
                    } else {
                        System.out.println("2- Parking: No violations");
                    }
                    if (arr[2] > 0) {
                        System.out.println("3- Running Red Light: " + arr[2] * 3 * 150);
                    } else {
                        System.out.println("3- Running Red Light: No violations");
                    }
                    if (arr[3] > 0) {
                        System.out.println("4- No License Plate: " + arr[3] * 3 * 1500);
                    } else {
                        System.out.println("4- No License Plate: No violations");
                    }
                    if (arr[4] > 0) {
                        System.out.println("5- No Registration: " + arr[4] * 3 * 5000);
                    } else {
                        System.out.println("5- No Registration: No violations");
                    }
                    if (arr[5] > 0) {
                        System.out.println("6- No Helmet: " + arr[5] * 200);
                    } else {
                        System.out.println("6- No Helmet: No violations");
                    }
                    break;
                case 3:
                    System.out.println("1- Speeding");
                    System.out.println("2- Parking");
                    System.out.println("3- Running Red Light");
                    System.out.println("4- No License Plate");
                    System.out.println("5- No Registration");
                    System.out.println("6- No Helmet");
                    String[] types = {"Speeding", "Parking", "Running Red Light", "No License Plate", "No Registration", "No Helmet"};
                    int[] multipliers = {300, 150, 450, 4500, 15000, 200};
                    System.out.print("Enter number of type: ");
                    int type = Exc.infinite(0, 6, 1);
                    if (arr[type - 1] > 0) {
                        System.out.println("Frequent Violations Report: " + arr[type - 1]);
                        System.out.println(types[type - 1] + ": " + arr[type - 1] * multipliers[type - 1]);
                    } else {
                        System.out.println(types[type - 1] + ": No violations");
                    }
                    break;
            }
            System.out.print("Do you want to continue (y/n):  ");
            p = input.nextLine().charAt(0);
        } while (p == 'y' || p == 'Y');
    }

    public static void z(ArrayList<Traffic_Violation> trafficViolations) {
        for (int i = 0; i < trafficViolations.size(); i++) {
            if (trafficViolations.get(i).getViolation_type().equals("Speeding"))
                arr[0]++;
            else if (trafficViolations.get(i).getViolation_type().equals("Parking"))
                arr[1]++;
            else if (trafficViolations.get(i).getViolation_type().equals("Running Red Light"))
                arr[2]++;
            else if (trafficViolations.get(i).getViolation_type().equals("No License Plate"))
                arr[3]++;
            else if (trafficViolations.get(i).getViolation_type().equals("No Registration"))
                arr[4]++;
            else if (trafficViolations.get(i).getViolation_type().equals("No Helmet"))
                arr[5]++;
        }
    }
}
