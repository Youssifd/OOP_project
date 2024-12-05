package Admin;
import Account.*;
import java.util.Scanner;
import static java.lang.System.out;
public class Admin extends Account {
    public int counter =0;
    public static Scanner cin=new Scanner(System.in);
    Admin(String id,String Name,String email,String Passowrd) {
        super(id, email, Passowrd, Name);
    }
    public boolean LoginAdmin() {

        do {
            String name;
            String pass = "";
            out.print("\"Enter username: \"");
            name = cin.nextLine();
            out.print("\"Enter Password: \"");
            pass = cin.nextLine();
            for (int i = 0; i < 6; i++) {
                if (name.equals("Marwan") && pass.equals("MS"))
                    return true;
            }
            counter++;
            out.println("invalid username or password");
        } while(counter <5);
      //  x=false;
        return false;
    }




}
