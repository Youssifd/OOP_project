import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Message {
    public String tile;
    public String message;
    Date MessageDate;
    public static Scanner input = new Scanner(System.in);
    public Message(String tile, String message) {
        this.tile = tile;
        this.message = message;
        MessageDate = new Date();
    }
    public static void send(ArrayList<Message> messages) {
        System.out.println("Enter the title of the message: ");
        String title = input.nextLine();
        System.out.println("Enter the message: ");
        String message = input.nextLine();
        Message msg = new Message(title, message);
        messages.add(msg);
        System.out.println("Message sent successfully!");

    }
}
