package Admin;
import java.util.ArrayList;
import Traffic.*;
public class TrafficReport {
    public static void generateHighDensityZonesReport(ArrayList<Traffic_Violation> traffic_Violation) {
        ArrayList<String> zones = new ArrayList<>();
        ArrayList<Integer> zoneCounts = new ArrayList<>();
        for (Traffic_Violation violation : traffic_Violation) {
            String zone = violation.getViolation_type();
            int index = zones.indexOf(zone);
            if (index != -1) {
                zoneCounts.set(index, zoneCounts.get(index) + 1);
            } else {
                zones.add(zone);
                zoneCounts.add(1);
            }
        }
        System.out.println("High-Density Zones Report:");
        for (int i = 0; i < zones.size(); i++) {
            System.out.println("Zone: " + zones.get(i) + " | Violations: " + zoneCounts.get(i));
        }
    }
    public static void generateFrequentViolationsReport(ArrayList<Traffic_Violation> traffic_Violation) {
        ArrayList<String> violationTypes = new ArrayList<>();
        ArrayList<Integer> violationCounts = new ArrayList<>();
        for (Traffic_Violation violation : traffic_Violation) {
            String violationType = violation.getViolation_type();
            int index = violationTypes.indexOf(violationType);
            if (index != -1) {
                violationCounts.set(index, violationCounts.get(index) + 1);
            } else {
                violationTypes.add(violationType);
                violationCounts.add(1);
            }
        }
        System.out.println("\nFrequent Violations Report:");
        for (int i = 0; i < violationTypes.size(); i++) {
            System.out.println("Violation: " + violationTypes.get(i) + " | Frequency: " + violationCounts.get(i));
        }
    }
}
