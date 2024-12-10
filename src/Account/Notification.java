package Account;

import Admin.Exc;
import Vehicle.Traffic_Violation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Notification {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM-HH:mm");
    static Scanner input = new Scanner(System.in);
    public String message;
    public String date;
    public String title;//
    public boolean isRead = false;

    public Notification(String message) {
        this.message = message;
        this.date = formatter.format(new Date());
        this.title = "TrafficPost-" + date.split("-")[1];
    }

    public static void showNotification(ArrayList<Notification> notifications, int num) {
        //when entering the notification page
        for (int i = 0, j = 0; i < notifications.size(); i++) {
            j = i + 1;
            if (notifications.get(i).isRead) {
                System.out.println(j + "- " + notifications.get(i).title + " (Seen)");
            } else {
                System.out.println(j + "- " + notifications.get(i).title + " (Unseen)");
            }
        }
        System.out.println("You Want view a notification? ? (Y/N)");
        char ch;
        do {
            ch = input.next().charAt(0);
            if (ch == 'N' || ch == 'n') return;

        } while (ch != 'Y' && ch != 'y');

        System.out.println("Enter the number of the notification you want to read or 0 to exit");
        int choice = 0;
        choice = Exc.infinite(0, notifications.size(), 1);
        if (!notifications.get(choice - 1).isRead) {
            num--;
            notifications.get(choice - 1).isRead = true;
        }
        System.out.println("_____________" + notifications.get(choice - 1).title + "___________");
        System.out.println("Sent on: " + notifications.get(choice - 1).date);
        System.out.println(notifications.get(choice - 1).message);

    }

    public static void sendNotification(ArrayList<Notification> notifications, String message) {
        Notification notification = new Notification(message);
        notifications.add(notification);
    }

    public static String StandardMessage(String OwnerName, Traffic_Violation tv) {
        String[] s = tv.getDate().split("-");
        return "Dear " + OwnerName + ",\n" +
                "We are sorry to inform you that your vehicle with the plate number " + tv.getVehicle_ID() + " has been caught violating the traffic rules.\n" +
                "The violation was issued on " + s[0] + " at " + s[1] + "\n" +
                "The violation was issued by " + tv.getWhoIssued() + "\n" +
                "The violation was issued in the zone " + tv.getZoneName() + "\n" +
                "The violation was issued for " + tv.getViolationID() + "\n" +
                "The fine for this violation is " + tv.getFine_amount() + "\n" +
                "Please make sure to pay the fine as soon as possible to avoid any further actions.\n" +
                "Best Regards,\n" +
                "Traffic Violation System";
    }


}
