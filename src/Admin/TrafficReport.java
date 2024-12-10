package Admin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Area.Zone;
import Vehicle.Traffic_Violation;

public class TrafficReport {

    // دالة لاختيار التقرير بناءً على نوعه
    public static void generateReportBasedOnChoice(ArrayList<Traffic_Violation> trafficViolations,ArrayList<Zone> zones) {
        // تسأل المستخدم عن نوع التقرير
        System.out.println("Choose the type of report you want:");
        System.out.println("1. High-Density Zones Report based on Time");
        System.out.println("2. Most Violated Zone");
        System.out.println("3. Most Frequent Violation Type");

        // قراءة اختيار المستخدم

        int choice=0;
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
        System.out.println("Time Period: " + userTime + " | Congestion Cause: " + congestionCause);
        System.out.println(" Zones Report (Generated at: " + reportTime + "):");
    }

    // تحديد سبب الازدحام بناءً على الوقت
    private static String getCongestionCause(LocalTime time) {
        if (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(17, 0))) {
            return "خروج الموظفين والطلاب"+"Higly Congested " ;
        } else if (time.isAfter(LocalTime.of(7, 0)) && time.isBefore(LocalTime.of(9, 0))) {
            return "الذروة الصباحية" + "Medium Congested " ;
        } else {
            return "لا يوجد ازدحام ملحوظ" + "Low Congested ";
        }
    }

    // تقرير أكثر نوع من المخالفات تكراراً
    public static void reportbymostviolation( ArrayList<Zone> zones) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String reportTime = LocalTime.now().format(timeFormatter);
        System.out.println("\nMost Violated Zone Report:");// الوقت الحالي

    for (int i = 0; i < zones.size(); i++)
    {
        if (zones.get(i).numViolationOccured>=0)
            System.out.println(zones.get(i).getName()+" | Violations: "+zones.get(i).numViolationOccured);
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
            else if (trafficViolations.get(i).getViolation_type().equals("Stop"))
                arr[1]++;
            else if (trafficViolations.get(i).getViolation_type().equals("Road_kill"))
            arr[2]++;
            else if (trafficViolations.get(i).getViolation_type().equals("Traffic_Jam"))
            arr[3]++;
            else if (trafficViolations.get(i).getViolation_type().equals("Yielding"))
            arr[4]++;
            else if (trafficViolations.get(i).getViolation_type().equals("Dangerous_Vehicle"))
            arr[5]++;
        }
        System.out.println("\nFrequent Violations Report:");
        System.out.println("Speeding: " + arr[0]);
        System.out.println("Stop: " + arr[1]);
        System.out.println("Road_kill: " + arr[2]);
        System.out.println("Traffic_Jam: " + arr[3]);
        System.out.println("Yielding: " + arr[4]);
        System.out.println("Dangerous_Vehicle: " + arr[5]);
        System.out.println("\nFrequent Violations Report (Generated at: " + reportTime + "):");

    }

    public static void main(String[] args) {
        ArrayList<Traffic_Violation> trafficViolations = new ArrayList<>();
        ArrayList<Zone> zones = new ArrayList<>();
        TrafficReport.generateReportBasedOnChoice(trafficViolations,zones);
    }
}
