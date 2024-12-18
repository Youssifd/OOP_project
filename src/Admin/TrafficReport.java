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
 int []arr=new int[6];
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
}
