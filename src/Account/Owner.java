package Account;

public class Owner extends Account {

    public String Contact_Num;
    Boolean isVerified = false; //from admin
    //list of vehicles
    //list of messages
    Owner(String ID, String Email, String Password, String Name, String Contact_Num) {
        super(ID, Email, Password, Name);
        this.Contact_Num = Contact_Num;

    }
}
