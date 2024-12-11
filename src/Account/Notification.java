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
    public String title;
    public String[] MessageData;
    public boolean isRead = false;


    public Notification(String message,String[] Data) {
        this.message = message;
        this.MessageData = Data;
        this.date = formatter.format(new Date());
        this.title = "TrafficPost-" + date.split("-")[1];
    }
    public Notification(String title,String Date,String[] Data,boolean isRead,String OwnerName) {
        this.date = Date;
        this.title = title;
        this.isRead = isRead;
        this.MessageData = Data;
        this.message = StandardForFile(OwnerName,this.MessageData);
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
            else if (ch == 'Y' || ch == 'y') break;
            else System.out.println("Invalid input, please enter Y or N");

        } while (true);

        System.out.println("Enter the number of the notification you want to read or 0 to exit");
        int choice = 0;
        choice = Exc.infinite(0, notifications.size(), 1);
        if (!notifications.get(choice - 1).isRead) {
            num--;
            notifications.get(choice - 1).isRead = true;
        }
        System.out.println("___________" + notifications.get(choice - 1).title + "___________");
        System.out.println("Sent on: " + notifications.get(choice - 1).date);
        System.out.println(notifications.get(choice - 1).message);

    }

    public static String StandardForFile(String OwnerName,String[] Str ) {

       String[] s = Str[1].split("-");
        return "Dear " + OwnerName + ",\n" +
                "We are sorry to inform you that your vehicle with the plate number " + Str[0] + " has been caught violating the traffic rules.\n" +//Str[0]->Vehicle_ID
                "The violation was issued on " +s[0] + " at " + s[1] + "\n" +//Str[1] -> Date
                "The violation was issued by " + Str[2] + "\n" +//Str[2] -> WhoIssued
                "The violation was issued in the zone " + Str[3] + "\n" +//Str[3] -> ZoneName
                "Violation Code is " + Str[4] + "\n" +// Str[4] -> ViolationID
                "The fine for this violation is " + Str[5] + "\n" +//Str[5] -> Fine_amount
                "Please make sure to pay the fine as soon as possible to avoid any further actions.\n" +
                "Best Regards,\n" +
                "Traffic Violation System";
    }

    public static String StandardMessage(String OwnerName, Traffic_Violation tv) {
        String[] s = tv.getDate().split("-");
        return "Dear " + OwnerName + ",\n" +
                "We are sorry to inform you that your vehicle with the plate number " + tv.getVehicle_ID() + " has been caught violating the traffic rules.\n" +
                "The violation was issued on " + s[0] + " at " + s[1] + "\n" +
                "The violation was issued by " + tv.getWhoIssued() + "\n" +
                "The violation was issued in the zone " + tv.getZoneName() + "\n" +
                "Violation Code is " + tv.getViolationID() + "\n" +
                "The fine for this violation is " + tv.getFine_amount() + "\n" +
                "Please make sure to pay the fine as soon as possible to avoid any further actions.\n" +
                "Best Regards,\n" +
                "Traffic Violation System";
    }


}
