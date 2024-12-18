package Admin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Scanner;

import Account.Owner;
import Area.Zone;
import File_function.File_Processing;
import Traffic_Officer.TrafficOfficer;
import Vehicle.Traffic_Violation;

public class TrafficReport {
public static int []arr=new int[6];
    // دالة لاختيار التقرير بناءً على نوعه
    public static void generateReportBasedOnChoice(ArrayList<Traffic_Violation> trafficViolations,ArrayList<Zone> zones) {
        // تسأل المستخدم عن نوع التقرير
        char c=0;
        do {
            System.out.println("1. High-Density Zones Report based on Time");
            System.out.println("2. Most Violated Zone");
            System.out.println("3. Most Frequent Violation Type");
            System.out.print("Choose the type of report you want: ");

            // قراءة اختيار المستخدم

            int choice = 0;
            choice = Exc.infinite(choice, 3, 1);
            // لتجاوز السطر الفارغ بعد قراءة الاختيار

            switch (choice) {
                case 1:
                    generateHighDensityZonesReportBasedOnTime(); // تقرير المناطق بناءً على الوقت
                    break;
                case 2:
                    reportbymostviolation(zones); // تقرير أكثر أنواع المخالفات تكراراً
                    break;
                case 3:
                    generateM(trafficViolations); // تقرير أكثر منطقة فيها مخالفات
                    break;

            }
            System.out.print("Do you want to continue(y/n): ");
           c=Admin.cin.next().charAt(0);
        } while (c=='y'||c=='Y');
    }

    // تقرير المناطق بناءً على الوقت
    public static void generateHighDensityZonesReportBasedOnTime() {
        // قراءة الوقت من المستخدم
        String inputTime;
        LocalTime userTime = null;
        boolean validTime = false;

        // التأكد من أن المستخدم يدخل وقتًا صحيحًا
        while (!validTime) {
            System.out.print("Enter the time (HH:mm:ss): ");
            inputTime = Admin.cin.nextLine();

            // تحقق من تنسيق الوقت باستخدام تعبير منتظم
            if (inputTime.matches("^([01]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])$")) {
                try {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    userTime = LocalTime.parse(inputTime, timeFormatter); // وقت المستخدم
                    validTime = true;  // الإدخال صحيح
                } catch (Exception e) {
                    System.out.println("Invalid time format. Please enter the time in HH:mm:ss format.");
                }
            } else {
                System.out.println("Invalid time format. Please enter the time in HH:mm:ss format.");
            }
        }

        String reportTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")); // الوقت الحالي

        // تحديد سبب الازدحام بناءً على الوقت
        String congestionCause = getCongestionCause(userTime);

        // طباعة التقرير
        System.out.println("Time Period: " + userTime + " | " + congestionCause);
        System.out.println(" Zones Report (Generated at: " + reportTime + "):");
    }

    // تحديد سبب الازدحام بناءً على الوقت
    private static String getCongestionCause(LocalTime time) {
        if (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(17, 0))) {
            return "There is traffic congestion during this period due to employees leaving work and students leaving schools and universities" ;
        } else if (time.isAfter(LocalTime.of(7, 0)) && time.isBefore(LocalTime.of(9, 0))) {
            return "\n" +
                    "There is traffic congestion during this period due to employees heading to work and students heading to schools and universities" ;
        } else {
            return "There is no noticeable traffic congestion during this period";
        }
    }

    // تقرير أكثر نوع من المخالفات تكراراً
    public static void reportbymostviolation( ArrayList<Zone> z ) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String reportTime = LocalTime.now().format(timeFormatter);
        System.out.println("\nMost Violated Zone Report:");// الوقت الحاليfor
        for (int i=0;i< z.size();i++)
            if (z.get(i).numViolationOccured>0)
            {
                System.out.println(z.get(i).getName()+" | Violations: "+z.get(i).numViolationOccured);
            }
        // إعداد بيانات التقرير
        // حساب تكرار كل نوع من المخالفات
        // طباعة التقرير
        System.out.println("\nFrequent Violations Report (Generated at: " + reportTime + "):");
    }

    // تقرير أكثر منطقة فيها مخالفات
    public static void generateM(ArrayList<Traffic_Violation> trafficViolations) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String reportTime = LocalTime.now().format(timeFormatter);

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
        System.out.println("\nFrequent Violations Report:");
        System.out.println("Speeding: " + arr[0]);
        System.out.println("Parking: " + arr[1]);
        System.out.println("Running Red Light: " + arr[2]);
        System.out.println("No License Plate: " + arr[3]);
        System.out.println("No Registration: " + arr[4]);
        System.out.println("No Helmet: " + arr[5]);
        System.out.println("\nFrequent Violations Report (Generated at: " + reportTime + "):");

    }
    public static void avg(ArrayList<Traffic_Violation> trafficViolations) {
        TrafficReport.z(trafficViolations);
        Scanner input;
        Integer x = 0;
        double average=0;
        int totalViolations = 0;
            x+=arr[0]*3*100;
            x+=arr[1]*3*50;
            x+=arr[2]*3*150;
            x+=arr[3]*3*1500;
            x+=arr[4]*3*5000;
            x+=arr[5]*200;
        for (int count : arr) {
            totalViolations += count; // اجمع عدد المخالفات من كل نوع
        }


            char p;
        int a=0;
        System.out.println("1- for all\n2- for each type\n3- specific type");
        do {
            input=new Scanner(System.in);
        System.out.print("Enter your choice: ");
        a=Exc.infinite(a,3,1);
        switch (a)
        {
            case 1:
                if (totalViolations > 0) {
                    average = (double) x / totalViolations; // احسب المتوسط

                } else {
                    System.out.println("No violations to calculate an average.");
                }
                System.out.println("Average of total violations =  " + average);
               break;
            case 2:
                System.out.println("Average of each type:");

                if (arr[0] > 0) {
                    System.out.println("1- Speeding: " + ((double) arr[0] * 3 * 100) / arr[0]);
                } else {
                    System.out.println("1- Speeding: No violations");
                }

                if (arr[1] > 0) {
                    System.out.println("2- Parking: " + ((double) arr[1] * 3 * 50) / arr[1]);
                } else {
                    System.out.println("2- Parking: No violations");
                }

                if (arr[2] > 0) {
                    System.out.println("3- Running Red Light: " + ((double) arr[2] * 3 * 150) / arr[2]);
                } else {
                    System.out.println("3- Running Red Light: No violations");
                }

                if (arr[3] > 0) {
                    System.out.println("4- No License Plate: " + ((double) arr[3] * 3 * 1500) / arr[3]);
                } else {
                    System.out.println("4- No License Plate: No violations");
                }

                if (arr[4] > 0) {
                    System.out.println("5- No Registration: " + ((double) arr[4] * 3 * 5000) / arr[4]);
                } else {
                    System.out.println("5- No Registration: No violations");
                }

                if (arr[5] > 0) {
                    System.out.println("6- No Helmet: " + ((double) arr[5] * 200) / arr[5]);
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
                int type = Exc.infinite(0, 6, 1); // إدخال صحيح بين 1 و 6

// التحقق من صحة الإدخال

                    if (arr[type - 1] > 0) { // التحقق من وجود مخالفات لهذا النوع
                        System.out.println(types[type - 1] + ": " + arr[type - 1] * multipliers[type - 1]);
                    } else {
                        System.out.println(types[type - 1] + ": No violations");
                    }


                break;
        }
        System.out.print("Do you want to continue (y/n):  ");
        p=input.nextLine().charAt(0);
        } while (p=='y'||p=='Y');
    }
    public static int[] z(ArrayList<Traffic_Violation> trafficViolations)
    {
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
        return arr;
    }
}
