package Account;

public abstract class Account {
    public static int Acc_counter = 0;
    private  String ID;
    public String Email; // use regex to validate email (admin , officer, user)
    // @gmail for user , @admin for admin , @officer for officer
    // if email is not valid , throw exception
    // if gmail search for user object(owner) , if admin search for admin object(admin) , if officer search for officer object
    protected String Password;
    public String Name;
    protected String Contact;

    public Account(String ID, String Email, String Password, String Name) {
        this.ID = ID;
        this.Email = Email;
        this.Password = Password;
        this.Name = Name;
        Acc_counter++;
    }

    public String getID() {
        return ID;
    }

}
