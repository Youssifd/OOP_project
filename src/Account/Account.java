package Account;

import java.util.ArrayList;

import Admin.Admin;

public abstract class Account {
    public static int Acc_counter = 0;
    private final String id;
    public String Email; // use regex to validate email (admin , officer, user)
    // @gmail for user , @admin for admin , @officer for officer
    // if email is not valid , throw exception
    // if gmail search for user object(owner) , if admin search for admin object(admin) , if officer search for officer object
    protected String Password;
    public String Name;
    public String Contact;
    public static ArrayList<String> Names = new ArrayList<>();
    public static ArrayList<String> ids = new ArrayList<>();

    public Account(String ID, String Email, String Password, String Name) {
        this.id = ID;
        this.Email = Email;
        this.Password = Password;
        this.Name = Name;
        Names.add(Name);
        ids.add(ID);
        Acc_counter++;
    }

    public String getPassword() {
        return this.Password;
    }

    // Password comparison method
    public boolean comparePassword(String inputPassword) {
        return this.Password.equals(inputPassword);
    }

    public String getID() {
        return id;
    }


    public static String Unique(String prompt, ArrayList<String> existingValues) {
        String input;
        System.out.print(prompt);
        while (true) {
            input = Admin.cin.nextLine();
            if (!existingValues.contains(input)) {
                existingValues.add(input);
                break;
            } else {
                System.out.println("Error: This value is already used. Please try again.");
                System.out.print("Enter new value: ");
            }
        }
        return input;
    }


}