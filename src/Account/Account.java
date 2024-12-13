package Account;

import java.util.ArrayList;

import Admin.*;

import static java.lang.System.out;

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
public static ArrayList<String> Emails=new ArrayList<>();
    public Account(String ID, String Email, String Password, String Name) {
        this.id = ID;
        this.Email = Email;
        this.Password = Password;
        this.Name = Name;
        Names.add(Name);
        ids.add(ID);
        Emails.add(Email);
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

            // Check if input contains a comma
         Exc.vald(input);

            // Check if input is unique
            if (existingValues.contains(input)) {
                System.out.println("Error: This value is already used. Please try again.");
                System.out.print("Enter new value: ");
            }
            // Valid input
            else {
                existingValues.add(input);
                break;
            }
        }
        return input;
    }

    protected void changePass() {
        out.println("Enter your current password: ");
        String CurrentPassword = Admin.cin.nextLine();
        do{
            CurrentPassword = Admin.cin.nextLine();
            if(!CurrentPassword.equals(Password)){
                out.println("Do you want to exit? (Y/N)");
                char ch;
                do {
                    ch = Admin.cin.next().charAt(0);
                    if (ch == 'Y' || ch == 'y') return;
                } while (ch != 'N' && ch != 'n');
                out.println("Incorrect password! Please try again.");
            }

        }while (!CurrentPassword.equals(Password));

        out.print("Enter your new password: ");
        String NewPassword;
        do{
            NewPassword = Admin.cin.nextLine();
            if(NewPassword.equals(Password)){
                out.println("Do you want to exit? (Y/N)");
                char ch;
                do {
                    ch = Admin.cin.next().charAt(0);
                    if (ch == 'Y' || ch == 'y') return;
                } while (ch != 'N' && ch != 'n');
                out.println("The new password is the same as the old one. \nPlease enter a different password.");
            }
        }while(NewPassword.equals(Password));

        Password = Admin.cin.nextLine();
        out.println("Password changed successfully!");
    }


}